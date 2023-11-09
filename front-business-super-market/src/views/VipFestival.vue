<template>
  <div>

    <el-card shadow="always" style="margin-bottom: 10px">
      <span style="font-weight:bold">节日管理</span>
      <el-alert
        style="text-align: left; margin-top: 10px"
        :closable="false"
        title="节日管理"
        description="添加不同节日信息，在节日和会员生日当天，发送自定义邮箱内容"
        show-icon
      >
      </el-alert>

      <div style="text-align: left; margin-bottom: 15px; margin-top: 20px">
        <button
          type="primary"
          size="small"
          @click="addDialogVisiable = true"
          class="css-button-rounded--green"
        >
          <i class="el-icon-plus"></i>&nbsp;添加
        </button>
        <button
          type="danger"
          size="small"
          @click="removeAll()"
          class="css-button-rounded--red"
        >
          <i class="el-icon-delete"></i>&nbsp;删除
        </button>
        <button
          type="success"
          size="small"
          class="css-button-rounded--green"
          @click="exportVip()"
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
        :data="festivals"
        tooltip-effect="dark"
        style="overflow:auto;"
        :default-sort="{ order: 'descending' }"
        :header-cell-style="{
          background: '#F5F7FA',
          'text-align': 'center',
          color: '#606266',
        }"
        stripe
        highlight-current-row
        @selection-change="handleSelectionChange"
        @row-click="handleRowClick"
        height="500px"
        width="1200px"
      >
        <el-table-column
          type="selection"
          width="55"
          class="myRedCheckBox"
        ></el-table-column>

        <el-table-column
          align="center"
          label="节日id"
          width="220"
          show-overflow-tooltip
          prop="jrId"
          sortable
        >
          <template slot-scope="scope">{{ scope.row.jrId }}</template>
        </el-table-column>

        <el-table-column
          align="center"
          label="节日名称"
          width="220"
          show-overflow-tooltip
          prop="jrName"
        >
          <template slot-scope="scope">{{ scope.row.jrName }}</template>
        </el-table-column>

        <el-table-column
          align="center"
          prop="jrBless"
          label="节日祝福语"
          width="220"
          show-overflow-tooltip
        >
          <template slot-scope="scope">{{ scope.row.jrBless }}</template>
        </el-table-column>

        <el-table-column
          align="center"
          prop="jrBless"
          label="节日时间"
          width="220"
          sortable
          show-overflow-tooltip
        >
          <template slot-scope="scope">{{ scope.row.jrTime }}</template>
        </el-table-column>

        <el-table-column label="操作" align="center">
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

      <!-- 添加节日信息对话框 -->
      <el-dialog
        title="添加节日信息"
        :visible.sync="addDialogVisiable"
        width="450px"
      >
        <el-form
          :model="festival"
          :ref="festival"
          label-width="100px"
          class="demo-ruleForm"
          style="text-align: center"
          status-icon
        >
          <el-row>
            <el-col :span="20">
              <el-form-item label="节日名称" prop="jrName">
                <el-input v-model="festival.jrName"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="20">
              <el-form-item label="节日祝福语" prop="jrBless">
                <el-input v-model="festival.jrBless"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="20">
              <el-form-item label="节日时间" prop="jrTime">
                <el-date-picker
                  v-model="festival.jrTime"
                  type="date"
                  value-format="MM-dd"
                  placeholder="选择日期"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancleAdd()">取 消</el-button>
          <el-button type="primary" @click="add()">确 定</el-button>
        </div>
      </el-dialog>

      <!-- 修改节日信息对话框 -->
      <el-dialog
        title="修改节日信息"
        :visible.sync="updateDialogVisiable"
        width="450px"
      >
        <el-form
          :model="festival"
          :ref="festival"
          label-width="100px"
          class="demo-ruleForm"
          style="text-align: center"
          status-icon
        >
          <el-row>
            <el-col :span="20">
              <el-form-item label="节日Id" prop="jrId">
                <el-input v-model="festival.jrId"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="20">
              <el-form-item label="节日名称" prop="jrName">
                <el-input v-model="festival.jrName"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="20">
              <el-form-item label="节日祝福语" prop="jrBless">
                <el-input v-model.number="festival.jrBless"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="20">
              <el-form-item label="节日时间" prop="jrTime">
                <el-date-picker
                  v-model="festival.jrTime"
                  type="date"
                  value-format="MM-dd"
                  placeholder="选择日期"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancleAdd()">取 消</el-button>
          <el-button type="primary" @click="update()">确 定</el-button>
        </div>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 多选框选中的数据，删除时传输后台这个
      multipleSelection: [],
      // 临时变量，记录多选框删除数据
      selectedRows: [],
      // 选中当前行高亮
      currentRow: null,
      // 所有节日信息
      festivals: [],
      //修改对话框
      addDialogVisiable: false,
      updateDialogVisiable: false,
      festival: {
        jrId: "",
        jrName: "",
        jrBless: "",
        jrTime: "",
      },
      //   修改下标
      updateIndex: "",
    };
  },
  created() {
    this.allFestivals();
  },
  methods: {
    // 删除
    remove(index,row){
        this.$confirm("此操作将删除信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$axios
            .get("http://127.0.0.1:8080/festival/remove/" + row.jrId)
            .then((resp) => {
              if (resp.data.code == 200) {
                this.festivals.splice(index, 1);
                this.$message.success("删除成功");
              } else {
                this.$message.error("删除失败");
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
    // 回显
    showUpdate(index, row) {
      this.updateDialogVisiable = true;
      this.updateIndex = index;
      this.festival.jrId = row.jrId;
      this.festival.jrName = row.jrName;
      this.festival.jrBless = row.jrBless;
      this.festival.jrTime = row.jrTime;
    },
    // 修改
    update() {
      // this.festival.jrTime = this.festival.jrTime.substring(5,11)
      console.log(this.festival.jrTime);
      this.$axios
        .post("http://127.0.0.1:8080/festival/update", this.festival)
        .then((resp) => {
          if (resp.data.code == 200) {
            this.$message.success("修改成功");
            this.updateDialogVisiable = false;
            this.festivals.splice(this.updateIndex, 1, this.festival);
            this.festival = {
              jrId: "",
              jrName: "",
              jrBless: "",
              jrTime: "",
            };
          }
        });
    },
    // 添加
    add() {
      // this.festival.jrTime = this.festival.jrTime.substring(5,11)
      this.$axios
        .post("http://127.0.0.1:8080/festival/add", this.festival)
        .then((resp) => {
          if (resp.data.code == 200) {
            this.$message.success("添加成功");
            this.addDialogVisiable = false;
            this.allFestivals();
            this.festival = {
              jrId: "",
              jrName: "",
              jrBless: "",
              jrTime: "",
            };
          }
        });
    },
    cancleAdd() {
      this.updateDialogVisiable = false;
      this.addDialogVisiable = false;
      this.festival = {
        jrId: "",
        jrName: "",
        jrBless: "",
        jrTime: "",
      };
    },
    // 查询所有节日信息
    allFestivals() {
      this.$axios.get("http://127.0.0.1:8080/festival/all").then((resp) => {
        this.festivals = resp.data.data;
      });
    },
    //点击一行也加入多选框
    handleRowClick(row, column, event) {
      // 点击某一行时，切换该行的勾选状态，并更新selectedRows数组
      this.$refs.multipleTable.toggleRowSelection(row);
      let index = this.selectedRows.indexOf(row);
      if (index > -1) {
        this.selectedRows.splice(index, 1);
      } else {
        this.selectedRows.push(row);
      }
    },
    // 用于保存选中的行
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
  },
};
</script>

<style>
</style>