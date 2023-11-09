<template>
    <div>
      <!-- 积分商品 -->
      <el-card shadow="always" style="margin-bottom: 10px">
        <span style="font-weight:bold">积分商品管理</span>
        <el-alert
          style="text-align: left; margin-top: 10px"
          :closable="false"
          title="积分商品管理"
          description="对积分商品的增删改查"
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
          :data="vipIndents"
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
          <el-table-column
            type="selection"
            width="55"
            class="myRedCheckBox"
          ></el-table-column>
  
          <el-table-column
            align="center"
            label="商品id"
            width="220"
            show-overflow-tooltip
            prop="viId"
            sortable
          >
            <template slot-scope="scope">{{ scope.row.viId }}</template>
          </el-table-column>
  
          <el-table-column
            align="center"
            label="商品名字"
            width="220"
            show-overflow-tooltip
            prop="viName"
          >
            <template slot-scope="scope">{{ scope.row.viName }}</template>
          </el-table-column>
  
          <el-table-column
            align="center"
            prop="viPoint"
            label="所需积分"
            width="220"
            sortable
            show-overflow-tooltip
          >
            <template slot-scope="scope">{{ scope.row.viPoint }}</template>
          </el-table-column>
  
          <el-table-column
            align="center"
            prop="viNumber"
            label="库存数量"
            width="220"
            sortable
            show-overflow-tooltip
          >
            <template slot-scope="scope">{{ scope.row.viNumber }}</template>
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
          title="添加积分商品信息"
          :visible.sync="addDialogVisiable"
          width="450px"
        >
          <el-form
            :model="vipIndent"
            :ref="vipIndent"
            label-width="100px"
            class="demo-ruleForm"
            style="text-align: center"
            status-icon
          >
            <el-row>
              <el-col :span="20">
                <el-form-item label="商品名称" prop="viName">
                  <el-input v-model="vipIndent.viName"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
  
            <el-row>
              <el-col :span="20">
                <el-form-item label="所需积分" prop="viPoint">
                  <el-input v-model="vipIndent.viPoint"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            
            <el-row>
              <el-col :span="20">
                <el-form-item label="库存数量" prop="viNumber">
                  <el-input v-model="vipIndent.viNumber"></el-input>
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
            :model="vipIndent"
            :ref="vipIndent"
            label-width="100px"
            class="demo-ruleForm"
            style="text-align: center"
            status-icon
          >

            <el-row>
              <el-col :span="20">
                <el-form-item label="商品名称" prop="viName">
                  <el-input v-model="vipIndent.viName"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
  
            <el-row>
              <el-col :span="20">
                <el-form-item label="所需积分" prop="viPoint">
                  <el-input v-model="vipIndent.viPoint"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
  
            <el-row>
              <el-col :span="20">
                <el-form-item label="库存数量" prop="viNumber">
                  <el-input v-model.number="vipIndent.viNumber"></el-input>
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
        // 所有信息
        vipIndents: [],
        //修改对话框
        addDialogVisiable: false,
        updateDialogVisiable: false,
        vipIndent: {
          viId:"",
          viName:"",
          viPoint:"",
          viNumber:"",
        },
        //   修改下标
        updateIndex: "",
      };
    },
    created() {
      this.allVipIndents();
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
              .get("http://127.0.0.1:8080/vipIndent/remove/" + row.viId)
              .then((resp) => {
                if (resp.data.code == 200) {
                  this.vipIndents.splice(index, 1);
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
        this.vipIndent.viId = row.viId;
        this.vipIndent.viName = row.viName;
        this.vipIndent.viPoint = row.viPoint;
        this.vipIndent.viNumber = row.viNumber;
      },
      // 修改
      update() {
        this.$axios
          .post("http://127.0.0.1:8080/vipIndent/update", this.vipIndent)
          .then((resp) => {
            if (resp.data.code == 200) {
              this.$message.success("修改成功");
              this.updateDialogVisiable = false;
              this.vipIndents.splice(this.updateIndex, 1, this.vipIndent);
              this.vipIndent = {
          viId:"",
          viName:"",
          viPoint:"",
          viNumber:"",
        };
            }
          });
      },
      // 添加
      add() {
        this.$axios
          .post("http://127.0.0.1:8080/vipIndent/add", this.vipIndent)
          .then((resp) => {
            if (resp.data.code == 200) {
              this.$message.success("添加成功");
              this.addDialogVisiable = false;
              this.allVipIndents();
              this.vipIndent = {
          viId:"",
          viName:"",
          viPoint:"",
          viNumber:"",
        };
            }
          });
      },
      cancleAdd() {
        this.updateDialogVisiable = false;
        this.addDialogVisiable = false;
        this.vipIndent = {
          viId:"",
          viName:"",
          viPoint:"",
          viNumber:"",
        };
      },
      // 查询所有信息
      allVipIndents() {
        this.$axios.get("http://127.0.0.1:8080/vipIndent/all").then((resp) => {
          this.vipIndents = resp.data.data;
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
  
  