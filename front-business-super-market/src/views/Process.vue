<template>
  <div>
    <el-card class="box-card">
       <el-steps :active="active" class="step" finish-status="success">
 <el-step title="审核单据"></el-step>
  <el-step title="身份验证"></el-step>
  <el-step title="返回货单列表"></el-step>
</el-steps>
<el-row v-if="active==3">
 <el-col :sm="12" :lg="6" class="col">
    <el-result icon="success" title="入库完成" subTitle="点击返回，返回货单列表">
      <template slot="extra">
        <el-button type="primary" size="medium" @click="Back">返回</el-button>
      </template>
    </el-result>
  </el-col>
  </el-row>
   <el-form
          ref="user"
          :model="user"
          label-width="100px"
          class="loginForm"
       v-if="active==1"
        >
        <el-form-item
            label="密码"
            
          >
            <el-col :span="8">
              <el-input
                v-model="user.userPwd"
                placeholder="请输入密码"
              ></el-input
            ></el-col>
          </el-form-item>
          <el-form-item>
            <el-col :span="8">
             <el-button type="primary" @click="toStep"
              >确认</el-button
            ></el-col>
          </el-form-item>
      
          </el-form>




 

<div style="margin-top:50px" v-if="active==0">
<el-descriptions title="审核入库">
    <el-descriptions-item label="申请人">{{goodsVO.user.userName}}</el-descriptions-item>
    <el-descriptions-item label="货物名称">{{goodsVO.cargo.cargoName}}</el-descriptions-item>
    <el-descriptions-item label="货物数量">{{goodsVO.goodsCount}}</el-descriptions-item>
    <el-descriptions-item label="货物单位">
      <el-tag size="small">{{goodsVO.goodsUnit}}</el-tag>
    </el-descriptions-item>
    <el-descriptions-item label="货物单号">{{goodsVO.goodsNumber}}</el-descriptions-item>
    <el-descriptions-item label="申请时间">{{goodsVO.goodsfromDate}}</el-descriptions-item>
    
    
</el-descriptions>
<el-button style="margin-top:50px;" @click="next">审核</el-button> 


</div>

</el-card>
<el-dialog title="审核" :visible.sync="dialogVisible">
       <el-form
          ref="statu"
          :model="statu"
          label-width="100px"
          class="login"
       
        >
       
         <el-form-item label="选择审核状态">
           <el-col :span="8">
          <el-select v-model="statu.statuId" placeholder="请选择">
            <el-option
              v-for="s in status"
              :key="s.statuId"
              :label="s.statuName"
              :value="s.statuId"
            >
            </el-option>
          </el-select>
          </el-col>
        </el-form-item>
          </el-form>
     

      <el-tree
         @check="handleCheck"
          :data=" warehouses"
          :props="defaultProps"
          show-checkbox
          :expand-on-click-node="false"
          :default-expanded-keys="defaultexpandedkeys"
          node-key="whId"
          default-expand-all
          :default-checked-keys="menucheckedkeys"
          ref="warehousetree"
          v-if="statu.statuId==2"
        ></el-tree>
      <div slot="footer" class="dialog-footer">
             <el-button type="primary" @click="confirm"
              >确认</el-button
            >
            <el-button type="primary" @click="Cancel"
              >取消</el-button
            >
      </div>
    </el-dialog>

</div>
  







</template>



<script>
  export default {
    data() {
      return {
        
        user:{userPwd:""},
        status:[{statuId:2,statuName:"通过审核"},{statuId:3,statuName:"驳回"}],
        statu:{},
        store:{},
        menucheckedkeys: [],
      defaultexpandedkeys: [],
      warehouses: [],
          defaultProps: {
        children: "children",
        label: "whName",
      },
        
        dialogVisible:false,
        active: 0,
        goodsVO:{cargo:{},user:{}}

      };
    },
   created() {
  this.getGoodsById(this.$route.params.storeapplyId);
  this. getAllWarehouse();
    
  },
    methods: {
    
      
      ChangeGoodsVo(){this.goodsVO.goodsState=this.statu.statuId
          this.$axios
        .post(
          "http://localhost:8080/goods/modify" 
        ,
          this.goodsVO
        )
        .then((resp) => {
          if(resp.data.code==200){
            
            this.goodsVO={  user: {},cargo:{} };
            this.statu={}
           
            }
            else if(resp.data.code==100){
                this.$message("修改失败"+JSON.stringify(resp.data.data))
            }  else if(resp.data.code==500){
                this.$message("修改失败"+JSON.stringify(resp.data.data))
            }
          
        });
      },
      Back(){
          this.$router.push("/goodsList");
      },
      handleCheck(){
        if(this.$refs.warehousetree
            .getCheckedKeys().length>1){
             this.menucheckedkeys=this.$refs.warehousetree
            .getCheckedKeys().splice(1,1)
             this.$refs.warehousetree.setCheckedKeys(this.menucheckedkeys);

            }
      },
      toStep(){
        
        this.$axios
          .post("http://localhost:8080/store/user/"+this.user.userPwd)
          .then((resp) => {
            if (resp.data.code == 200) {
              this.$message("密码正确");
             this.active=3;
             if(this.statu.statuId==2){
             this.Submit();
             
             }
             this.ChangeGoodsVo();
             
             
              
              
            }else{this.$message( JSON.stringify(resp.data.data))}
          })
      },
      confirm(){this.dialogVisible=false;
         this.active=1;
      },
      Cancel(){this.dialogVisible=false;
      this.menucheckedkeys = [];
      this.$refs.warehousetree.setCheckedKeys(this.menucheckedkeys);
      },
      Submit(){
        let newstore={cargo:{cargoId:this.goodsVO.cargo.cargoId},cgUnit:this.goodsVO.goodsUnit,wareHouse:{whId:this.$refs.warehousetree
            .getCheckedKeys()[0]},cgCount:this.goodsVO.goodsCount}
        this.$axios
          .post("http://localhost:8080/store/update", newstore)
          .then((resp) => {
            if (resp.data.code == 200) {
              this.$message("添加成功");

             this.menucheckedkeys=[]
             this.$nextTick(() => {
      this.$refs.warehousetree.setCheckedKeys(this.menucheckedkeys);
  })
           
            
              
              
            }
          })
      },
       getAllWarehouse() {
      this.$axios.get("http://localhost:8080/goods/get/allwh").then((resp) => {
        if (resp.data.code == 200) {
          this.warehouses = resp.data.data;
          
        }
      });
    },
      getGoodsById(storeapplyId){
       this.$axios
        .post(
          "http://localhost:8080/goods/id/"+storeapplyId ).then((resp)=>{
            this.goodsVO=resp.data.data
          })

      },
      next() {
        this.dialogVisible=true
        //if (this.active++ > 2) this.active = 0;
      }
    }
  }
</script>

<style>
.col{
  margin-left: 430px;
}
 .el-tree .el-tree-node .is-leaf + .el-checkbox .el-checkbox__inner{
    display: inline-block;
  }
  .el-tree .el-tree-node .el-checkbox .el-checkbox__inner{
    display: none;
  }
  .el-tree-node.is-parent.is-leaf .el-checkbox .el-checkbox__inner {
  display: inline-block;
}



.loginForm{
  margin-left: 30%;
  margin-top:100px;
}
.step{
    text-align:left

}


  .box-card {
   height:500px;
  }


</style>