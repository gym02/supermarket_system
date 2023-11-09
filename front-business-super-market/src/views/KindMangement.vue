<template>
  <div>
    <!------------------------------ 商品类别数据搜索框 --------------------------------------------->
    <el-card shadow="always" style="margin-bottom: 10px;">
    <div style="margin-bottom: 5px">
      <el-form
        ref="form"
        size="small"
        label-width="80px"
        style="margin-top: 23px"
      >
        <el-row :gutter="20">
           
          <el-col :span="6">
            <el-form-item label="商品类别">
              <el-input v-model="kindDTO.zlName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">    
          <el-col :span="2" style="margin-left: 15px">
            <div>
              <button type="button" size="small" @click="search()" class="css-button-rounded--green"
                ><i class="el-icon-search"></i>&nbsp;搜索</button
              >
            </div>
          </el-col>
          <el-col :span="1">
            <div>
              <button type="success" size="small" @click="reset()" class="css-button-rounded--green"
                ><i class="el-icon-refresh"></i>&nbsp;重置</button
              >
            </div>
          </el-col>
        </el-row>
      </el-form>
    </div>
    </el-card>
    <!-------------------------------------- 商品类别数据显示表 ------------------------------------>
    <el-card shadow="always">
    <div>
      <div style="text-align: left; margin-bottom: 15px">
        <button
          type="button"
          size="small"
         
          @click="dialogVisible = true"
          class="css-button-rounded--green"
        >
          <i class="el-icon-plus"></i>&nbsp;添加
        </button>
        <button type="danger" size="small" @click="removeAll()" class="css-button-rounded--red">
          <i class="el-icon-delete"></i>&nbsp;删除
        </button>
        <!-- <button type="success" size="small" class="css-button-rounded--green">
          <i class="el-icon-upload2"></i>&nbsp;导出
        </button>
        <button type="warning" size="small" class="css-button-rounded--green">
          <i class="el-icon-download"></i>&nbsp;导入
        </button> -->
        <span style="text-align: right; float: right; margin: 15px">
          <el-button size="small" plain type="info">
            <i class="el-icon-s-operation"></i>
          </el-button>
        </span>
      </div>

      <el-table
        border
        ref="multipleTable"
        :data="kinds"
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
      >
        <el-table-column type="selection" width="55" class="myRedCheckBox"> </el-table-column>
        <el-table-column
          align="center"
          label="商品类别编号"
          width="400"
          show-overflow-tooltip
          sortable
          prop="zlId"
          fixed
        >
        </el-table-column>
       <el-table-column
          align="center"
          label="商品类别"
          width="400"
          show-overflow-tooltip
          sortable
          prop="zlName"
          fixed
        >
        </el-table-column>
        
        <el-table-column label="操作" fixed="right" align="center">
            <template slot-scope="scope">
              <button
                size="small"
                plain
                type="warning"
                @click="showUpdate(scope.$index, scope.row)"
                class="css-button-rounded--yellow"
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
    </div>
  </el-card>
    <!-- 对话框 -->
    <el-dialog title="show" :visible.sync="dialogVisible">
      <el-form ref="form" :model="kind" label-width="80px">
          <el-col :span="12">
            <el-form-item label="商品类别">
              <el-input v-model="kind.zlName"></el-input>
            </el-form-item>
          </el-col>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancle()">取 消</el-button>
        <el-button type="primary" @click="addOrUpdate">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 对话框添加或删除标记
      addFlag: true,
      // 存储修改行的下标
      updateIndex: "",
      //表格显示数据，与后端进行绑定
      kinds: [
      ],
      // 显示添加对话框
      dialogVisible: false,
      // 添加员工绑定属性
      kind: {
        zlId: "",
        zlName:""
       
      },
      // 搜索员工绑定属性
      kindDTO: {
        zlId: "",
        zlName:""
      },
      // 多选框选中的数据，删除时传输后台这个
      multipleSelection: [],
      // 临时变量，记录多选框删除数据
      selectedRows:[],
      // 选中当前行高亮
      currentRow: null,
    };
  },
  mounted(){
      this.search();
  },
  methods: {
    // 添加人员
    addOrUpdate() {
      if (this.addFlag == true) {
        this.$axios
          .post("http://localhost:8080/kind/add", this.kind)
          .then(r => {
            if (r.data.code === 200) {
              this.$message.success("添加成功");
              this.search();
            } else{
              this.$message.error("添加失败")
            }
          });
      
        this.dialogVisible = false;
        this.kind = {};
      } else {
        // 修改
        console.log(this.kind);
        // 修改
        this.$axios
          .post("http://localhost:8080/kind/edit", this.kind)
          .then(r => {
            if (r.data.code == 200) {
              this.$message.success("修改成功");
              this.search();
            } else{
              this.$message.error("修改失败")
              this.search();
            }
              this.dialogVisible = false;
              this.addFlag;
              this.kind = {};
          });
        
      }
    },
    // 取消添加人员
    cancle() {
      this.dialogVisible = false;
      // 这样清空真的很快，不用傻乎乎每个属性都赋空
      this.kind = {};
    },
    // 删除人员
    remove(index, row) {
      this.$confirm("此操作将删除信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
          .then(() => {
            this.$axios
          .post("http://localhost:8080/kind/deleteByZlId/"+row.zlId)
          .then(r=>{
            if(r.data.code===200){
              this.$message.success("删除成功")
              this.search();
            }else{
              this.$message.error("删除失败")
            }
          })
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除"
            });
          });
    },
    // 数据回显
    showUpdate(index, row) {
      this.addFlag = false;
      this.kind ={...row};
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
      if(this.multipleSelection.length==0){
        this.$message.error("请至少选中一条数据");
        return
      }
      this.$confirm('此操作将批量删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
         let zlIds=[]
          for(let m of this.multipleSelection){
            zlIds.push(m.zlId)
          }
          this.$axios.post("http://localhost:8080/kind/removeAll",{
            zlIds
          }).then(r=>{
            if(r.data.code===200){
              this.$message.success("删除成功")
              this.search();
            }else{
              this.$message.error("删除失败")
            }
          })
      })
      .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除"
            });
          });
    },
    // 搜索人员
    search() {
      this.$axios.post("http://localhost:8080/kind/getAll",this.kindDTO)
      .then(r=>{
        if(r.data.code===200){
          console.log(this.kindDTO)
          this.kinds=r.data.data;
        }
      })
    },
    // 搜索重置
    reset() {
      this.kindDTO = {};
      this.$message({
        type: "success",
        message: "重置成功",
      });
    },


    // 部门标签相关方法
    formatter(row, column) {
      return row.department;
    },
    // 部门标签相关方法
    filterTag(value, row) {
      return row.department === value;
    },
    // 部门标签相关方法
    filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
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
/* 黄色按钮 */
.css-button-rounded--yellow {
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
  background: #f5cd07;
  margin: 3px;
}
.css-button-rounded--yellow:hover {
  background: #ffe566;
}


</style>