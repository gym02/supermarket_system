<template>
  <div>
    <!------------------------------ 员工数据搜索框 --------------------------------------------->
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
            <el-form-item label="订单状态">
              <el-select v-model="goodsDTO.goodsState" placeholder="请选择" @change="changeValue">
                <el-option
                  v-for="s in states"
                  :key="s.stateId"
                  :label="s.stateName"
                  :value="s.stateId"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          
        </el-row>
      </el-form>
    </div>
    </el-card>
    <!-------------------------------------- 员工数据显示表 ------------------------------------>
    <el-card shadow="always">
    <div>
      <div style="text-align: left; margin-bottom: 15px">
        <button
          type="primary"
          size="small"
         
          @click="addGoods"
          class="css-button-rounded--green"
        >
          <i class="el-icon-plus"></i>&nbsp;添加
        </button>
        <button type="danger" size="small" @click="removeAll()" class="css-button-rounded--red">
          <i class="el-icon-delete"></i>&nbsp;删除
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
        :data="goods"
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
          label="申请号"
          width="80"
          show-overflow-tooltip
       
          prop="storeapplyId"
          fixed
        >
        </el-table-column>
        <el-table-column
          align="center"
          label="货物名"
          width="150"
          show-overflow-tooltip
          prop="cargo.cargoName"
        >
        </el-table-column>
        <el-table-column
          align="center"
          prop="goodsCount"
          label="货物数量"
          width="150"
          show-overflow-tooltip
          sortable
        >
          
        </el-table-column>
        <el-table-column
          align="center"
          prop="goodsUnit"
          label="货物单价"
          width="100"
         
        >
    
        </el-table-column>
        <el-table-column
          align="center"
          prop="goodsNumber"
          label="申请编号"
          width="150"
          show-overflow-tooltip
          sortable
        >
         
        </el-table-column>
        <el-table-column
          align="center"
          prop="goodsfromDate"
          label="申请日期"
          width="150"
          show-overflow-tooltip
          sortable
        >
          
        </el-table-column>
        <el-table-column
          align="center"
          prop="user.userName"
          label="经办人"
          width="150"
          show-overflow-tooltip
          sortable
        >
          
        </el-table-column>
         <el-table-column
         align="center"
        label="货物状态">
    <template slot-scope="scope">
        <span v-if="scope.row.goodsState==1">申请中</span>
        <span v-if="scope.row.goodsState==2">通过审核</span>
        <span v-if="scope.row.goodsState==3">驳回</span>
    </template>
   </el-table-column>
        

        <el-table-column label="操作" fixed="right" align="center" >
          <template slot-scope="scope">
            <button
              size="small"
              plain
              type="warning"
              @click="showUpdate(scope.$index, scope.row)"
              class="css-button-rounded--green"
              v-if="scope.row.goodsState==1||scope.row.goodsState==3"
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
              <button
              size="small"
              plain
              type="warning"
              @click="handle(scope.$index, scope.row)"
              class="css-button-rounded--green"
              v-if="scope.row.goodsState==1||scope.row.goodsState==3"
            >
              <i class="el-icon-edit"></i>&nbsp;审核
            </button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </el-card>
    <!-- 对话框 -->
    <el-dialog title="修改员工" :visible.sync="dialogVisible">
      <el-form ref="form" :model="goodsVO" label-width="60px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="申请号">
              <el-input v-model="goodsVO.storeapplyId" :disabled="true" ></el-input>
            </el-form-item>
          </el-col>
         <el-col :span="12">
            <el-form-item label="名称">
              <el-select v-model="goodsVO.cargo.cargoId" placeholder="请选择">
                <el-option
                  v-for="s in cargos"
                  :key="s.cargoId"
                  :label="s.cargoName"
                  :value="s.cargoId"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
           <el-col :span="12">
            <el-form-item label="数量">
              <el-input v-model="goodsVO.goodsCount"></el-input>
            </el-form-item>
          </el-col>
          
          <el-col :span="11">
            <el-form-item label="单位">
              <el-input v-model="goodsVO.goodsUnit"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="编号">
              <el-input v-model="goodsVO.goodsNumber" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="日期">
              <el-date-picker
                type="date"
                placeholder="选择日期"
                v-model="goodsVO.goodsfromDate"
                value-format="yyyy-MM-dd"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="经办人">
              <el-input v-model="goodsVO.user.userName" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancle()">取 消</el-button>
        <el-button type="primary" @click="Update">确 定</el-button>
      </div>
    </el-dialog>
    <div>
    
       <el-pagination
          @size-change="changeSize"
          @current-change="changeNum"
          :current-page="goodsDTO.pageNum"
          :page-sizes="[2, 4, 6, 8]"
          :page-size="goodsDTO.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
  </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      cargos:[],
   
      states:[{stateId:1,stateName:"申请中"},{stateId:2,stateName:"通过审核"},{stateId:3,stateName:"驳回"}],
      goodsVO: { user: {},cargo:{} },
     
    
    total: 0,
    goods: [],
    goodsDTO: {
         pageNum: 1,
         pageSize: 2 },
      // 对话框添加或删除标记
      addFlag: true,
      // 存储修改行的下标
      updateIndex: "",
      
     
      
      // 显示添加对话框
      dialogVisible: false,
    
      
      
      // 多选框选中的数据，删除时传输后台这个
      multipleSelection: [],
      // 临时变量，记录多选框删除数据
      selectedRows:[],
      // 选中当前行高亮
      currentRow: null,
    };
  },
  created() {
   this. getAllGoods();
   this.getAllCargos();
    
  },

  methods: {
    handle(index,row){
      this.$router.push({path:"/process/"+row.storeapplyId})
    },
     getAllCargos() {
      
      this.$axios
        .post("http://localhost:8080/goods/cargo")
        .then((resp) => {
          this.cargos = resp.data.data;
        });
    },
    changeValue(){
      this.goodsDTO.pageNum=1;
      this.getAllGoods();
    },
    exportExcel(){
        /**
         * 下载文件的功能不能使用axios发请求，因为axios的响应会被封装到res中，在前端没办法处理。
         * 只能使用原生的浏览器直发请求
         */
        let url = "http://localhost:8080/employee/export";
        url += "?pageSize="+this.employeeDTO.pageSize;
        url += "&pageNum="+this.employeeDTO.pageNum;
        if(this.employeeDTO.empName){
          url += "&empName="+this.employeeDTO.empName;
        }

        if(this.employeeDTO.empId){
          url += "&empId="+this.employeeDTO.empId;
        }
        if(this.employeeDTO.empGender){
          url += "&empGender="+this.employeeDTO.empGender;
        }
        if(this.employeeDTO.empAge){
          url += "&empAge="+this.employeeDTO.empAge;
        }
         if(this.employeeDTO.empPhone){
          url += "&empPhone="+this.employeeDTO.empPhone;
        }
          if(this.employeeDTO.from){
          url += "&from="+this.employeeDTO.from;
        }
         if(this.employeeDTO.to){
          url += "&to="+this.employeeDTO.to;
        }


        window.location.href = url;},
     deleteBatch(){
     

        this.$axios
        .post(
          "http://localhost:8080/goods/removebatch" 
     ,
          this.multipleSelection
        )
        .then((resp) => {
           this.$message("删除成功");
          this.getAllGoods();
         
        });
    },
   
     getAllGoods() {
      
       
      this.$axios
        .post(
          "http://localhost:8080/goods/get/all" ,
           
          this.goodsDTO
        )
        .then((resp) => {
          this.goods = resp.data.data.beanList;
          this.total = resp.data.data.totalRecord;
        });
    },
      changeSize(size) {
      this.goodsDTO.pageSize = size;
      this.goodsDTO.pageNum = 1;
      this.getAllGoods();
    },
    
    changeNum(pageNum) {
      this.goodsDTO.pageNum = pageNum;
      this.getAllGoods();
    }
  ,
    addGoods(){
         this.$router.push("/goodsAdd");
    },
    
    Update() {
            this.$axios
        .post(
          "http://localhost:8080/goods/modify" 
        ,
          this.goodsVO
        )
        .then((resp) => {
          if(resp.data.code==200){
            this.$message("修改成功")
            this.goodsVO={  user: {},cargo:{} };
            this.dialogVisible=false
            this.getAllGoods();
            }
            else if(resp.data.code==100){
                this.$message("修改失败"+JSON.stringify(resp.data.data))
            }  else if(resp.data.code==500){
                this.$message("修改失败"+JSON.stringify(resp.data.data))
            }
          
        });
     
    },
    
    cancle() {
      this.dialogVisible = false;
      // 这样清空真的很快，不用傻乎乎每个属性都赋空
      this.  goodsVO= {  user: {},cargo:{} };
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
        .post("http://localhost:8080/goods/delete/" + row.storeapplyId)
        .then((resp) => {
         if(resp.data.code==200){
         this.$message("删除成功");
          this.goodsDTO.pageNum=1;
          this.getAllGoods();}
        });
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
    
    
      this.dialogVisible = true;
      this.goodsVO = {...row}
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
      this.$confirm('此操作将批量删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        
        this.$axios
        .post(
          "http://localhost:8080/goods/removebatch" 
     ,
          this.multipleSelection
        )
        .then((resp) => {
           if(resp.data.code==200){
           this.$message("删除成功");
          this.getAllGoods();}
         
        });
        
      })
      .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除"
            });
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