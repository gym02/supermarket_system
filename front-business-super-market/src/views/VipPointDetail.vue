<template>
  <div>
    <!------------------------------ 兑换记录数据搜索框 --------------------------------------------->
    <el-card shadow="always" style="margin-bottom: 10px">
      <div style="margin-bottom: 5px">
        <el-form
          ref="form"
          size="small"
          label-width="80px"
          style="margin-top: 23px"
        >
          <el-row :gutter="20">
            <el-col :span="6">
              <el-form-item label="商品编号">
                <el-input v-model="searchPerson.viId"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="商品名称">
                <el-input v-model="searchPerson.viName"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="会员编号">
                <el-input v-model="searchPerson.hyId"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20">
            <el-col :span="6">
              <el-form-item label="会员姓名">
                <el-input v-model="searchPerson.hyName"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="会员邮箱">
                <el-input v-model="searchPerson.gkEmail"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="兑换时间">
                <el-date-picker
                  v-model="searchPerson.dhTime"
                  type="datetime"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  placeholder="选择日期时间"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>

            <el-col
              :span="2"
              style="margin-left: 10px; margin-top: -7px; padding-top: -5px"
            >
              <div>
                <button
                  type="button"
                  size="small"
                  @click="
                    search();
                    $message.success('搜索成功');
                  "
                  class="css-button-rounded--green"
                >
                  <i class="el-icon-search"></i>&nbsp;搜索
                </button>
              </div>
            </el-col>
            <el-col
              :span="1"
              style="margin-left: -6px; margin-top: -7px; padding-top: -5px"
            >
              <div>
                <button
                  type="button"
                  size="small"
                  @click="reset()"
                  class="css-button-rounded--green"
                >
                  <i class="el-icon-refresh"></i>&nbsp;重置
                </button>
              </div>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </el-card>
    <!-------------------------------------- 兑换记录数据显示表 ------------------------------------>
    <el-card shadow="always" style="overflow: auto">
      <div>
        <div style="text-align: left; margin-bottom: 15px">
          <button
            type="primary"
            size="small"
            @click="$router.push('/vipPoint')"
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
          :data="allPerson"
          tooltip-effect="dark"
          style="width: 100%"
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
          height="420"
          width="100%"
        >
          <el-table-column type="selection" width="55" class="myRedCheckBox">
          </el-table-column>

          <el-table-column
            align="center"
            label="兑换编号"
            width="140"
            show-overflow-tooltip
            sortable
            prop="dhId"
          >
            <template slot-scope="scope">{{ scope.row.dhId }}</template>
          </el-table-column>

          <el-table-column
            align="center"
            label="会员编号"
            width="140"
            show-overflow-tooltip
            sortable
            prop="hyId"
          >
            <template slot-scope="scope">{{ scope.row.hyId }}</template>
          </el-table-column>

          <el-table-column
            align="center"
            label="会员姓名"
            width="140"
            show-overflow-tooltip
            prop="hyName"
          >
            <template slot-scope="scope">{{ scope.row.hyName }}</template>
          </el-table-column>

          <el-table-column
            align="center"
            label="会员邮箱"
            width="140"
            show-overflow-tooltip
            prop="gkEmail"
          >
            <template slot-scope="scope">{{ scope.row.gkEmail }}</template>
          </el-table-column>

          <el-table-column
            align="center"
            label="商品编号"
            width="140"
            show-overflow-tooltip
            prop="viId"
          >
            <template slot-scope="scope">{{ scope.row.viId }}</template>
          </el-table-column>

          <el-table-column
            align="center"
            prop="viName"
            label="商品名称"
            width="140"
            show-overflow-tooltip
            sortable
          >
            <template slot-scope="scope">{{ scope.row.viName }}</template>
          </el-table-column>

          <el-table-column
            align="center"
            prop="dhTime"
            label="兑换时间"
            width="160"
            show-overflow-tooltip
            sortable
          >
            <template slot-scope="scope">{{ scope.row.dhTime }}</template>
          </el-table-column>

          <el-table-column label="操作" align="center">
            <template slot-scope="scope">

              <button
                size="small"
                type="button"
                @click="remove(scope.$index, scope.row)"
                class="css-button-rounded--red"
              >
                <i class="el-icon-delete"></i>&nbsp;删除
              </button>
            </template>
          </el-table-column>
        </el-table>

        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="searchPerson.pageNum"
          :page-sizes="[5, 10, 20, 40]"
          :page-size="searchPerson.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :pager-count="5"
          :total="total"
        >
        </el-pagination>
      </div>
    </el-card>

    <!-- 修改兑换信息对话框 -->
    <el-dialog title="修改会员信息" :visible.sync="dialogVisible" width="450px">
      <el-form
        :model="person"
        :ref="person"
        label-width="100px"
        class="demo-ruleForm"
        style="text-align: center"
        :rules="rules"
        status-icon
      >
        <el-row>
          <el-col :span="20">
            <el-form-item label="会员卡号" prop="hyId">
              <el-input v-model="person.hyId" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="20">
            <el-form-item label="会员姓名" prop="hyName">
              <el-input v-model="person.hyName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="20">
            <el-form-item label="会员邮箱" prop="gkEmail">
              <el-input v-model="person.gkEmail"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="20">
            <el-form-item label="余额" prop="hyBalance">
              <el-input v-model.number="person.hyBalance"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="20">
            <el-form-item label="会员积分" prop="hyPoint">
              <el-input v-model.number="person.hyPoint"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="20">
            <el-form-item label="总积分" prop="hyPoint">
              <el-input v-model.number="person.hyTotalPoint"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancle()">取 消</el-button>
        <el-button type="primary" @click="update()">确 定</el-button>
      </div>
    </el-dialog>
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
      // 数据总数
      total: 0,
      // 表格当前页
      currentPage: "",
      // 分页属性
      pageDto: {
        pageNum: 1,
        pageSize: 5,
      },
      // 存储修改行的下标
      updateIndex: "",
      //所有vip信息
      allPerson: [],
      // 显示添加对话框
      dialogVisible: false,
      // 修改员工绑定属性
      person: {
        viId: "",
        viName: "",
        viPoint: "",
        hyId: "",
        hyName: "",
        gkEmail: "",
        dhTime: "",
      },
      // 搜索员工绑定属性
      searchPerson: {
        viId: "",
        viName: "",
        hyId: "",
        hyName: "",
        gkEmail: "",
        dhTime: "",
        pageNum: 1,
        pageSize: 5,
      },
      // 多选框选中的数据，删除时传输后台这个
      multipleSelection: [],
      // 临时变量，记录多选框删除数据
      selectedRows: [],
      // 选中当前行高亮
      currentRow: null,
      // 前端数据校验规则
      rules: {
        hyName: [
          { required: true, message: "请输入会员名字", trigger: "blur" },
          { min: 2, max: 4, message: "长度在 2 到 4 个字符", trigger: "blur" },
        ],
        gkEmail: [
          { required: true, message: "请输入邮箱地址", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],
        hyBalance: [
          { required: true, validator: checkBalance, trigger: "blur" },
        ],
        hyPoint: [{ required: true, validator: checkBalance, trigger: "blur" }],
      },
    };
  },
  created() {
    this.search();
  },

  methods: {
    // 分页方法
    handleSizeChange(size) {
      this.searchPerson.pageSize = size;
      this.searchPerson.pageNum = 1;
      this.search();
    },
    handleCurrentChange(num) {
      this.searchPerson.pageNum = num;
      this.search();
    },
    // 删除人员
    remove(index, row) {
      this.$confirm("此操作将删除信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$axios
            .get("http://127.0.0.1:8080/vipExchange/remove/" + row.dhId)
            .then((resp) => {
              if (resp.data.code == 200) {
                this.allPerson.splice(index, 1);
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
    // 数据回显
    showUpdate(index, row) {
      this.person.gkId = row.gkId;
      this.person.hyId = row.hyId;
      this.person.hyName = row.hyName;
      this.person.hyUsername = row.hyUsername;
      this.person.gkEmail = row.gkEmail;
      this.person.hyLevel = row.hyLevel;
      this.person.hyDiscount = row.hyDiscount;
      this.person.hyBalance = row.hyBalance;
      this.person.hyPoint = row.hyPoint;
      this.person.hyTotalPoint = row.hyTotalPoint;

      this.updateIndex = index;
      this.dialogVisible = true;
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
    // 删除所有人员
    removeAll() {
      this.$confirm("此操作将批量删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          let ids = [];
          for (let i = 0; i < this.multipleSelection.length; i++) {
            ids.push(this.multipleSelection[i].hyId);
          }
          this.$axios
            .post("http://127.0.0.1:8080/vip/removeAllVip", ids)
            .then((resp) => {
              if (resp.data.code == 200) {
                // console.log(this.multipleSelection);
                let val = this.multipleSelection; //选中的值
                if (val) {
                  val.forEach((val, index) => {
                    // 这块看不明白的话看下面的解释
                    this.allPerson.forEach((v, i) => {
                      if (val === v) {
                        this.allPerson.splice(i, 1);
                      }
                    });
                  });
                }
                this.$message.success("删除成功");
              } else {
                this.$message.error("删除失败,未选择数据");
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
    // 取消修改人员
    cancle() {
      this.dialogVisible = false;
      this.person.hyId = "";
      this.person.hyName = "";
      this.person.hyUsername = "";
      this.person.gkEmail = "";
      this.person.hyLevel = "";
      this.person.hyDiscount = "";
      this.person.hyBalance = "";
      this.person.hyPoint = "";
      this.person.hyTotalPoint = "";
    },
    // 修改人员
    update() {
      this.$axios
        .post("http://127.0.0.1:8080/vip/updateVip", this.person)
        .then((resp) => {
          if (resp.data.code == 200) {
            this.$message.success("修改成功");
            this.allPerson.splice(this.updateIndex, 1, this.person);
            this.person = {};
            this.dialogVisible = false;
          } else if (resp.data.code == 401) {
            this.$message.error("输入数据非法");
          } else {
            this.$message.error("该用户不存在");
          }
        });
    },
    // 搜索人员
    search() {
      this.$axios
        .post("http://127.0.0.1:8080/vipExchange/search", this.searchPerson)
        .then((resp) => {
          if (resp.data.code == 200) {
            this.total = resp.data.data.total;
            this.allPerson = resp.data.data.list;
          } else {
            this.$message.error("未知错误,查询异常");
          }
        });
    },
    // 搜索重置
    reset() {
      this.searchPerson = {
        viId: "",
        viName: "",
        hyId: "",
        hyName: "",
        gkEmail: "",
        dhTime: "",
        pageNum: 1,
        pageSize: 5,
      };
      this.search();
      this.$message({
        type: "success",
        message: "重置成功",
      });
    },
  },
};
</script>
  
  <style>
/* 绿色按钮 */
.css-button-rounded--green {
  min-width: 80px;
  height: 40px;
  color: #fff;
  padding: 5px 10px;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  outline: none;
  border-radius: 5px;
  border: 2px solid white;
  background: #57cc99;
  margin: 3px;
}
.css-button-rounded--green:hover {
  background: #6ef2b9;
}
/* 红色按钮 */
.css-button-rounded--red {
  min-width: 80px;
  height: 40px;
  color: #fff;
  padding: 5px 10px;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  outline: none;
  border-radius: 5px;
  border: 2px solid white;
  background: #f34646;
  margin: 3px;
}
.css-button-rounded--red:hover {
  background: #ff7e91;
}
</style>