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
            <el-form-item label="员工号">
              <el-input v-model="employeeDTO.empId"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="姓名">
              <el-input v-model="employeeDTO.empName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="性别">
              <el-radio label="男" v-model="employeeDTO.empGender">男</el-radio>
              <el-radio label="女" v-model="employeeDTO.empGender">女</el-radio>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="门店">
              <el-select v-model="employeeDTO.mdId" placeholder="请选择">
                <el-option
                  v-for="s in shops"
                  :key="s.mdId"
                  :label="s.mdName"
                  :value="s.mdId"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="电话">
              <el-input v-model="employeeDTO.empPhone"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="起始日期">
              <el-date-picker
                type="date"
                v-model="employeeDTO.from"
                placeholder="选择日期"
                value-format="yyyy-MM-dd"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
           <el-col :span="6">
            <el-form-item label="结束日期">
              <el-date-picker
                type="date"
                v-model="employeeDTO.to"
                placeholder="选择日期"
                value-format="yyyy-MM-dd"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="2" style="margin-left: 15px">
            <div>
              <button type="button" size="small" @click="getAllEmployees" class="css-button-rounded--green"
                ><i class="el-icon-search"></i>&nbsp;搜索</button
              >
            </div>
          </el-col>
          <el-col :span="1">
            <div>
              <button type="button" size="small" @click="reset()" class="css-button-rounded--green"
                ><i class="el-icon-refresh"></i>&nbsp;重置</button
              >
            </div>
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
         
          @click="addEmployee"
          class="css-button-rounded--green"
        >
          <i class="el-icon-plus"></i>&nbsp;添加
        </button>
        <button type="danger" size="small" @click="removeAll()" class="css-button-rounded--red">
          <i class="el-icon-delete"></i>&nbsp;删除
        </button>
        <button type="success" size="small" @click="exportExcel" class="css-button-rounded--green">
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
        :data="employees"
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
          label="员工号"
          width="150"
          show-overflow-tooltip
          sortable
          prop="empId"
          fixed
        >
        </el-table-column>
        <el-table-column
          align="center"
          label="姓名"
          width="150"
          show-overflow-tooltip
          prop="empName"
        >
        </el-table-column>
        <el-table-column
          align="center"
          prop="empGender"
          label="性别"
          width="150"
          show-overflow-tooltip
          sortable
        >
          
        </el-table-column>
        <el-table-column
          align="center"
          prop="shop.mdName"
          label="门店"
          width="100"
         
        >
    
        </el-table-column>
        <el-table-column
          align="center"
          prop="empPhone"
          label="电话号码"
          width="150"
          show-overflow-tooltip
          sortable
        >
         
        </el-table-column>
        <el-table-column
          align="center"
          prop="empDate"
          label="添加日期"
          width="150"
          show-overflow-tooltip
          sortable
        >
          
        </el-table-column>
         <el-table-column
            label="汽车图片"
            width="100">
      <template slot-scope="scope"><img :src="'http://localhost:8080/images/'+scope.row.file.path" width="80px" height="40px"></template>
    </el-table-column>
        

        <el-table-column label="操作" fixed="right" align="center" >
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
    </div>
  </el-card>
    <!-- 对话框 -->
    <el-dialog title="修改员工" :visible.sync="dialogVisible">
      <el-form ref="form" :model="employeeVO" label-width="60px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="员工号">
              <el-input v-model="employeeVO.empId" :disabled="true" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="姓名">
              <el-input v-model="employeeVO.empName" :disabled="true" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="性别">
              <el-radio label="男" v-model="employeeVO.empGender">男</el-radio>
              <el-radio label="女" v-model="employeeVO.empGender">女</el-radio>
            </el-form-item>
          </el-col>
          <el-col :span="4"> &nbsp; </el-col>
          <el-col :span="12">
            <el-form-item label="门店">
              <el-select v-model="employeeVO.shop.mdId" placeholder="请选择">
                <el-option
                  v-for="s in shops"
                  :key="s.mdId"
                  :label="s.mdName"
                  :value="s.mdId"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="电话">
              <el-input v-model="employeeVO.empPhone"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="日期">
              <el-date-picker
                type="date"
                placeholder="选择日期"
                v-model="employeeVO.empDate"
                value-format="yyyy-MM-dd"
              >
              </el-date-picker>
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
          :current-page="employeeDTO.pageNum"
          :page-sizes="[1, 2, 3, 4]"
          :page-size="employeeDTO.pageSize"
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
       employeeVO: { file: { id: null }, shop: { mdId: null }, user: {} },
        shops:[  { mdId: 1, mdName: "万达" },
        { mdId: 2, mdName: "家乐福" }],
    
    total: 0,
    employees: [],
    employeeDTO: {empId:'',
                  empName:'',
                  empAge:'',
                  empPhone:'',
                  from:'',
                  to:'',
                  mdId:'',
         pageNum: 1,
    pageSize: 1 },
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
   this. getAllEmployees();
    
  },

  methods: {
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
          "http://localhost:8080/employee/removebatch" 
     ,
          this.multipleSelection
        )
        .then((resp) => {
           this.$message("删除成功");
          this.getAllEmployees();
         
        });
    },
   
     getAllEmployees() {
        console.log(this.employeeDTO.empId)
      this.$axios
        .post(
          "http://localhost:8080/employee/get/all" ,
           
          this.employeeDTO
        )
        .then((resp) => {
          this.employees = resp.data.data.beanList;
          this.total = resp.data.data.totalRecord;
        });
    },
      changeSize(size) {
      this.employeeDTO.pageSize = size;
      this.employeeDTO.pageNum = 1;
      this.getAllEmployees();
    },
    
    changeNum(pageNum) {
      this.employeeDTO.pageNum = pageNum;
      this.getAllEmployees();
    },
     myinit(){
      this.$axios
        .post(
          "http://localhost:8080/employee/get/all" ,
          this.employeeDTO
        )
        .then((resp) => {
          
          this.employees = resp.data.data.beanList;
          this.total = resp.data.data.totalRecord;
          if(resp.data.code==200){
            this.getAllDepartments();
          }
        });
    }
  ,
    addEmployee(){
         this.$router.push("/employeeAdd");
    },
    
    Update() {
            this.$axios
        .post(
          "http://localhost:8080/employee/modify" 
        ,
          this.employeeVO
        )
        .then((resp) => {
          if(resp.data.code==200){
            this.$message("修改成功")
            this.employeeVO={ file: { id: null }, shop: { mdId: null }, user: {} };
            this.dialogVisible=false
            this.getAllEmployees();
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
      this.  employeeVO= { file: { id: null }, shop: { mdId: null }, user: {} };
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
        .post("http://localhost:8080/employee/delete/" + row.empId)
        .then((resp) => {
         if(resp.data.code==200){
         this.$message("删除成功");
          this.getAllEmployees();}
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
      this.employeeVO = {...row}
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
          "http://localhost:8080/employee/removebatch" 
     ,
          this.multipleSelection
        )
        .then((resp) => {
           if(resp.data.code==200){
           this.$message("删除成功");
          this.getAllEmployees();}
         
        });
        
      })
      .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除"
            });
          });
    },
    
    
    // 搜索重置
    reset() {
      this.employeeDTO = {};
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