<template>
  <div  >
    <!------------------------------ 员工数据搜索框 --------------------------------------------->
    <el-card shadow="always" style="margin-bottom: 10px;height:300px">
   
    <div style="margin-bottom: 5px">
      <el-form
        ref="form"
        size="small"
        
        label-width="80px"
        style="margin-top: 23px"
      >
       

        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="选择仓库">
         
      <el-cascader
     :props="defaultPropss"
    :options="warehouses"
    @change="handleXXX"
    v-model="storeDTO.whId"
    clearable> </el-cascader>
            </el-form-item>
          </el-col>
          
        </el-row>
           <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="货物名称">
               <el-select v-model="storeDTO.cgId" placeholder="请选择" @change="changeValue">
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
           <el-col :span="7">
            <div>
              <button type="button" size="small" @click="reset()" class="css-button-rounded--green"
                ><i class="el-icon-refresh"></i>&nbsp;重置</button
              >
            </div>
          </el-col>
        </el-row>

      </el-form>
       <div class="Echarts" >
    <div id="main" style="width: 500px;height:300px;margin-left:500px;position:relative;top:-150px"></div>
  </div>
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
     <button type="success" size="small" @click="exportExcel" class="css-button-rounded--green">
          <i class="el-icon-upload2"></i>&nbsp;导出
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
        :data="stores"
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
      
        @row-click="handleRowClick"
      >
        
        <el-table-column
          align="center"
          label="货物名称"
          width="200"
          show-overflow-tooltip
       
          prop="cargo.cargoName"
          fixed
        >
        </el-table-column>
     
     
        <el-table-column
          align="center"
          prop="cgCount"
          label="货物数量"
          width="200"
         
        >
    
        </el-table-column>
           <el-table-column
          align="center"
          prop="cgUnit"
          label="货物单位"
          width="200"
          show-overflow-tooltip
          sortable
        >
          
        </el-table-column>
        <el-table-column
          align="center"
         prop="pwareHouse.whName"
          label="存货仓库"
          width="300"
          show-overflow-tooltip
          sortable
        >
       
     
   
       
        </el-table-column>
        <el-table-column
          align="center"
          prop="wareHouse.whName"
          label="仓位"
          width="300"
          show-overflow-tooltip
          sortable
        >
          
        </el-table-column>
      
        

    
      </el-table>
    </div>
  </el-card>
   
    <div>
    
       <el-pagination
          @size-change="changeSize"
          @current-change="changeNum"
          :current-page="storeDTO.pageNum"
          :page-sizes="[1, 4, 6, 8]"
          :page-size="storeDTO.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
  </el-pagination>
    </div>
  </div>
</template>

<script>



export default {
    name: 'Echarts',
  data() {
    return {
       names:[],
            counts:[],
      warehouse:{},
      total: 0,
  
   storeDTO: {
         pageNum: 1,
         pageSize: 1 },
      stores:[],  
      warehouses:[],
      defaultPropss: {
            emitPath:false,
            value:"whId",
            
        children: "children",
        label: "whName",
      },
      cargos:[],
   
      states:[{stateId:1,stateName:"申请中"},{stateId:2,stateName:"通过审核"},{stateId:3,stateName:"驳回"}],
      goodsVO: { user: {},cargo:{} },
     
    
 
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
   this. getAllStores();
   this.getAllCargos();
 
    
  },
  
 

  methods: {

       async  getMap(){
        
    await   this.$axios
        .post(
          "http://localhost:8080/store/map/" +this.storeDTO.whId
        )
        .then((resp) => {
          if(resp.data.code==200){
          this.names = resp.data.data[0];
          this.counts = resp.data.data[1];
         
         
         
          ;}
          
        
         
        }); 
        this.myEcharts();
    }, myEcharts(){
            
		  // 基于准备好的dom，初始化echarts实例
		  var myChart = this.$echarts.init(document.getElementById('main'));
            
    

		  // 指定图表的配置项和数据
		  var option = {
			  title: {
				  text: 'ECharts 入门示例'
			  },
			  tooltip: {},
			  legend: {
				  data:['货物数量']
			  },
			  xAxis: {
				  data: this.names
			  },
			  yAxis: {},
			  series: [{
				  name: '货物数量',
				  type: 'bar',
				  data: this.counts
			  }]
		  };

		  // 使用刚指定的配置项和数据显示图表。
		  myChart.setOption(option);
		  },
     exportExcel(){
        /**
         * 下载文件的功能不能使用axios发请求，因为axios的响应会被封装到res中，在前端没办法处理。
         * 只能使用原生的浏览器直发请求
         */
        let url = "http://localhost:8080/store/export";
        url += "?pageSize="+this.storeDTO.pageSize;
        url += "&pageNum="+this.storeDTO.pageNum;
        if(this.storeDTO.cgId){
          url += "&cgId="+this.storeDTO.cgId;
        }

        if(this.storeDTO.whId){
          url += "&empId="+this.storeDTO.whId;
        }
       


        window.location.href = url;},
    reset(){this.storeDTO= {
         pageNum: 1,
         pageSize: 1 }
    this.getAllStores();
    },
    changeValue(){
      this.pageNum=1;
      this.getAllStores();},

        clearChildren(arr){
        for(let i=0;i<arr.length;i++){
          if(!arr[i].children.length){
            arr[i].children=null
          }else{
            this.clearChildren(arr[i].children)
          }
        }
      },
    getAllWarehouse() {
      this.$axios.get("http://localhost:8080/goods/get/allwh").then((resp) => {
        if (resp.data.code == 200) {
          this.warehouses = resp.data.data;
          this.clearChildren(this.warehouses);
         
        }
      });
    },
    handleXXX(value){
      this.pageNum=1;
     this.getMap();
    this.getAllStores();},
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
   
   
    
   
     getAllStores() {
      
       
      this.$axios
        .post(
          "http://localhost:8080/store/get/all" ,
           
          this.storeDTO
        )
        .then((resp) => {
          if(resp.data.code==200){
          this.stores = resp.data.data.beanList;
          this.total = resp.data.data.totalRecord;
         
          this.getAllWarehouse();
         
          ;}
          
        
         
        });
    },
      changeSize(size) {
      this.storeDTO.pageSize = size;
      this.storeDTO.pageNum = 1;
      this.getAllStores();
    },
    
    changeNum(pageNum) {
      this.storeDTO.pageNum = pageNum;
      this.getAllStores();
    }
  ,
    addGoods(){
         this.$router.push("/goodsAdd");
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