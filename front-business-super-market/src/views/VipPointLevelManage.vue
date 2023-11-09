<template>
  <div>
    <!-- 积分和会员等级映射关系 -->
    <el-card shadow="always" style="margin-bottom: 10px">
      <span style="font-weight:bold">等级映射</span>
      <el-alert
        style="text-align: left; margin-top: 10px"
        :closable="false"
        title="等级映射关系"
        description="积分转换成会员等级的映射关系"
        show-icon
      >
      </el-alert>

      <div style="text-align: left; margin-bottom: 15px; margin-top: 20px">
        <button
          type="primary"
          size="small"
          class="css-button-rounded--green"
        >
          <i class="el-icon-plus"></i>&nbsp;添加
        </button>
        <button
          type="danger"
          size="small"
          class="css-button-rounded--red"
        >
          <i class="el-icon-delete"></i>&nbsp;删除
        </button>
        <button
          type="success"
          size="small"
          class="css-button-rounded--green"
        >
          <i class="el-icon-upload2"></i>&nbsp;导出
        </button>
        <button type="warning" size="small" class="css-button-rounded--green">
          <i class="el-icon-download"></i>&nbsp;导入
        </button>
        <span style="text-align: right; float: right; margin: 15px">
          <el-button size="small" plain type="info">
            <i class="el-icon-s-operation"></i>
          </el-button>
        </span>
      </div>

      <el-table
        border
        ref="multipleTable"
        :data="levels"
        tooltip-effect="dark"
        style="width: 100%; margin-top: 20px"
        :default-sort="{ order: 'descending' }"
        :header-cell-style="{
          background: '#F5F7FA',
          'text-align': 'center',
          color: '#606266',
        }"
        stripe
        highlight-current-row
      >
        <el-table-column
          align="center"
          label="会员等级"
          width="300"
          show-overflow-tooltip
          prop="djName"
          fixed
        >
          <template slot-scope="scope"
            >vip{{ scope.row.djId }}:{{ scope.row.djName }}</template
          >
        </el-table-column>

        <el-table-column
          align="center"
          label="所需积分"
          width="300"
          show-overflow-tooltip
          prop="minMax"
        >
          <template slot-scope="scope">{{ scope.row.minMax }}</template>
        </el-table-column>

        <el-table-column
          align="center"
          prop="discount"
          label="优惠"
          width="300"
          show-overflow-tooltip
        >
          <template slot-scope="scope"
            >{{ scope.row.djDiscount * 100 }}%</template
          >
        </el-table-column>

        <el-table-column label="操作" fixed="right" align="center">
          <template slot-scope="scope">
            <button
              size="small"
              plain
              type="warning"
              @click="showUpdate(scope.$index, scope.row)"
              class="css-button-rounded--green"
            >
              <i class="el-icon-edit"></i>&nbsp;编辑
            </button>

            <button
              size="small"
              type="danger"
              @click="remove(scope.$index, scope.row)"
              class="css-button-rounded--red"
            >
              <i class="el-icon-delete"></i>&nbsp;删除
            </button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 修改会员等级对话框 -->
      <el-dialog
        title="修改会员等级"
        :visible.sync="dialogVisiable"
        width="450px"
      >
        <el-form
          :model="level"
          :ref="level"
          label-width="100px"
          class="demo-ruleForm"
          style="text-align: center"
          :rules="rules"
          status-icon
        >
          <el-row>
            <el-col :span="20">
              <el-form-item label="会员等级" prop="djId">
                <el-input v-model="level.djId" :disabled="true"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="20">
              <el-form-item label="等级称号" prop="djName">
                <el-input v-model="level.djName"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="20">
              <el-form-item label="最小积分" prop="djMin">
                <el-input v-model.number="level.djMin"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="20">
              <el-form-item label="最大积分" prop="djMax">
                <el-input v-model.number="level.djMax"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="20">
              <el-form-item label="折扣率" prop="djDiscount">
                <el-input v-model="level.djDiscount"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancle()">取 消</el-button>
          <el-button type="primary" @click="update()">确 定</el-button>
        </div>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    // 检验输入余额数据合法性
    var checkBalance = (rule, value, callback) => {
      if (value == 0) {
      } else {
        if (!value) {
          return callback(new Error("请输入初始值"));
        }
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error("请输入数字值"));
        } else {
          if (value < 0) {
            callback(new Error("数值不能小于0"));
          } else {
            callback();
          }
        }
      }, 1000);
    };
    return {
      // 修改了min,max标记
      minFlag:"",
      maxFlag:"",
      // 显示对话框
      dialogVisiable: false,
      levelCount: 2,
      updateIndex:"",
      levels: [],
      level: {
        djId: "",
        djName: "",
        djMin: "",
        djMax: "",
        djDiscount: "",
      },
    //   数据校验规则
    rules:{
        djMin: [{ required: true, validator: checkBalance, trigger: "blur" }],
        djMax: [{ required: true, validator: checkBalance, trigger: "blur" }],
    }
    };
  },
  created() {
    this.allLevels();
  },
  methods: {
    // 查询所有会员等级信息
    allLevels() {
      this.$axios.get("http://127.0.0.1:8080/vip/allVipLevels").then((resp) => {
        if (resp.data.code == 200) {
          this.levels = resp.data.data;
          for (let i of resp.data.data) {
            i.minMax = i.djMin + "-" + i.djMax;
          }
        }
      });
    },
    changeLevelCount() {
      this.levelCount = parseInt(this.levelCount);
    },
    // 删除会员等级
    remove(index, row) {
      this.$confirm("此操作将删除信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$axios
            .get("http://127.0.0.1:8080/vip/removeVipLevel/" + row.djId)
            .then((resp) => {
              if (resp.data.code == 200) {
                this.levels.splice(index, 1);
                this.$message.success("删除成功");
              }
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    // 显示修改对话框
    showUpdate(index, row) {
      this.minFlag = row.djMin;
      this.maxFlag = row.djMax;
      this.dialogVisiable = true;
      this.level.djId = row.djId;
      this.level.djName = row.djName;
      this.level.djMin = row.djMin;
      this.level.djMax = row.djMax;
      this.level.djDiscount = row.djDiscount;
      this.updateIndex = index;
    },
    //取消修改
    cancle() {
      this.dialogVisiable = false;
      this.level.djId = "";
      this.level.djName = "";
      this.level.djMin = "";
      this.level.djMax = "";
      this.level.djDiscount = "";
    },
    // 修改
    update() {
      // console.log(this.level)
      this.$axios
        .post("http://127.0.0.1:8080/vip/updateVipLevel" , this.level)
        .then((resp) => {
          if (resp.data.code == 200) {
            this.allLevels();
            this.$message.success("修改成功");
            this.dialogVisiable = false;
          }
        });
    },
  },
};
</script>

<style>
</style>