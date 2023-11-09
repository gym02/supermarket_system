<template>
  <div>
    <!------------------------------ 充值记录数据搜索框 --------------------------------------------->
    <el-card shadow="always" style="margin-bottom: 10px">
      <div style="margin-bottom: 5px">
        <el-form
          ref="form"
          size="small"
          label-width="80px"
          style="margin-top: 23px"
        >
          <el-row :gutter="20">
            <el-col :span="5">
              <el-form-item label="交易id">
                <el-input v-model="searchBalanceDetail.czId"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="5">
              <el-form-item label="会员id">
                <el-input v-model="searchBalanceDetail.hyId"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="5">
              <el-form-item label="会员姓名">
                <el-input v-model="searchBalanceDetail.hyName"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="5">
              <el-form-item label="充值金额">
                <el-input v-model="searchBalanceDetail.czAmount"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20">
            <el-col :span="5">
              <el-form-item label="支付类型">
                <el-select
                  v-model="searchBalanceDetail.czType"
                  placeholder="请选择"
                >
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="5">
              <el-form-item label="交易时间">
                <el-date-picker
                  v-model="searchBalanceDetail.czTimeArray"
                  type="datetimerange"
                  :picker-options="pickerOptions"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  align="right"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>

            <el-col
              :span="2"
              style="margin-left: 270px; margin-top: -7px; padding-top: -5px"
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
    <!-------------------------------------- 充值记录数据显示表 ------------------------------------>
    <el-card shadow="always" style="overflow: auto">
      <div>
        <div style="text-align: left; margin-bottom: 15px">
          <button
            type="primary"
            size="small"
            @click="$router.push('/vipBalance')"
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
            @click="exportVipBalance()"
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
          :data="allBalanceDetail"
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
            label="交易id"
            width="140"
            show-overflow-tooltip
            sortable
            prop="czId"
          >
            <template slot-scope="scope">{{ scope.row.czId }}</template>
          </el-table-column>

          <el-table-column
            sortable
            align="center"
            label="会员id"
            width="140"
            show-overflow-tooltip
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
            prop="czAmount"
            label="充值金额"
            width="140"
            show-overflow-tooltip
            sortable
          >
            <template slot-scope="scope">{{ scope.row.czAmount }}</template>
          </el-table-column>

          <el-table-column
            align="center"
            prop="czType"
            label="交易类型"
            width="140"
            show-overflow-tooltip
          >
            <template slot-scope="scope">{{ scope.row.czType }}</template>
          </el-table-column>

          <el-table-column
            align="center"
            prop="czTime"
            label="充值时间"
            width="140"
            show-overflow-tooltip
            sortable
          >
            <template slot-scope="scope">{{ scope.row.czTime }}</template>
          </el-table-column>

          <el-table-column
            align="center"
            prop="czStatue"
            label="充值状态"
            width="140"
            :filters="[
              { text: '成功', value: '成功' },
              { text: '失败', value: '失败' },
            ]"
            :filter-method="filterTag"
            filter-placement="bottom-end"
          >
            <template slot-scope="scope">
              <el-tag
                :type="scope.row.czStatue === '成功' ? 'success' : 'error'"
                disable-transitions
                >{{ scope.row.czStatue }}</el-tag
              >
            </template>
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
          :current-page="searchBalanceDetail.pageNum"
          :page-sizes="[5, 10, 20, 40]"
          :page-size="searchBalanceDetail.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :pager-count="5"
          :total="total"
        >
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
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
      // 交易状态
      options: [
        {
          value: "成功",
          label: "成功",
        },
        {
          value: "失败",
          label: "失败",
        },
      ],
      // 多选框选中的数据，删除时传输后台这个
      multipleSelection: [],
      // 临时变量，记录多选框删除数据
      selectedRows: [],
      // 选中当前行高亮
      currentRow: null,
      // 时间选择快捷选项
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
      // 充值类型
      options: [
        { value: "微信", label: "微信" },
        { value: "支付宝", label: "支付宝" },
        { value: "现金", label: "现金" },
      ],
      // 数据搜素
      searchBalanceDetail: {
        czId: "",
        hyId: "",
        hyName: "",
        czAmount: "",
        czType: "",
        czTime:"",
        czTimeArray: "",
        pageNum: 1,
        pageSize: 5,
      },
      // 所有交易信息
      allBalanceDetail: [],
    };
  },
  created() {
    this.search();
  },
  methods: {
    // 分页方法
    handleSizeChange(size) {
      this.searchBalanceDetail.pageSize = size;
      this.searchBalanceDetail.pageNum = 1;
      this.search();
    },
    handleCurrentChange(num) {
      this.searchBalanceDetail.pageNum = num;
      this.search();
    },
    // 搜索
    search() {
      if (this.searchBalanceDetail.czTimeArray == "") {
        this.searchBalanceDetail.czTimeArray = null;
      }
      this.$axios
        .post(
          "http://127.0.0.1:8080/vip/searchBalanceDetail",
          this.searchBalanceDetail
        )
        .then((resp) => {
          if (resp.data.code == 200) {
            this.total = resp.data.data.total;
            this.allBalanceDetail = resp.data.data.list;
          } else {
            this.$message.error("未知错误,查询异常");
          }
        });
    },
    // 重置
    reset() {
      this.$message.success("重置成功");
      this.searchBalanceDetail = {
        czId: "",
        hyId: "",
        hyName: "",
        czAmount: "",
        czType: "",
        czTimeArray: "",
        pageNum: 1,
        pageSize: 5,
      };
      this.search();
    },
    // 删除所有充值记录
    removeAll() {
      this.$confirm("此操作将批量删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          let ids = [];
          for (let i = 0; i < this.multipleSelection.length; i++) {
            ids.push(this.multipleSelection[i].czId);
          }
          this.$axios
            .post("http://127.0.0.1:8080/vip/removeAllBalanceDetail", ids)
            .then((resp) => {
              if (resp.data.code == 200) {
                let val = this.multipleSelection; //选中的值
                if (val) {
                  val.forEach((val, index) => {
                    // 这块看不明白的话看下面的解释
                    this.allBalanceDetail.forEach((v, i) => {
                      if (val === v) {
                        this.allBalanceDetail.splice(i, 1);
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

    // 导出
    // 导出excel
    exportVipBalance() {
      let url = "http://localhost:8080/vip/exportVipBalance";
      url += "?pageSize=" + this.searchBalanceDetail.pageSize;
      url += "&pageNum=" + this.searchBalanceDetail.pageNum;
      if (this.searchBalanceDetail.czId) {
        url += "&czId=" + this.searchBalanceDetail.czId;
      }
      if (this.searchBalanceDetail.hyId) {
        url += "&hyId=" + this.searchBalanceDetail.hyId;
      }
      if (this.searchBalanceDetail.hyName) {
        url += "&hyName=" + this.searchBalanceDetail.hyName;
      }
      if (this.searchBalanceDetail.czAmount) {
        url += "&czAmount=" + this.searchBalanceDetail.czAmount;
      }
      if (this.searchBalanceDetail.czType) {
        url += "&czType=" + this.searchBalanceDetail.czType;
      }
      if (this.searchBalanceDetail.czTime) {
        url += "&czTime=" + this.searchBalanceDetail.czTime;
      }
      if (this.searchBalanceDetail.czStatue) {
        url += "&czStatue=" + this.searchBalanceDetail.czStatue;
      }
      window.location.href = url;
    },
    // 删除
    remove(index, row) {
      this.$confirm("此操作将删除信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$axios
            .get("http://127.0.0.1:8080/vip/removeBalanceDetail/" + row.czId)
            .then((resp) => {
              if (resp.data.code == 200) {
                this.allBalanceDetail.splice(index, 1);
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
    // 标签相关方法
    formatter(row, column) {
      return row.czStatue;
    },
    // 标签相关方法
    filterTag(value, row) {
      return row.czStatue === value;
    },
    // 标签相关方法
    filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    },
    // 用于保存选中的行
    handleSelectionChange(val) {
      this.multipleSelection = val;
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
  },
};
</script>

<style>
</style>

