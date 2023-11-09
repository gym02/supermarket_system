<template>
  <div>
    <!-- 角色列表 -->
    <div>
      <el-table :data="roles" style="width: 100%">
        <el-table-column prop="rid" label="角色编号" style="width: 10%">
        </el-table-column>
        <el-table-column prop="rname" label="角色名称" style="width: 20%">
        </el-table-column>
        <el-table-column label="操作" style="width: 50%"> 
          <template slot-scope="scope">
            <div>
              <el-button type="primary" plain icon="el-icon-plus" @click="addRole" circle size="mini"></el-button>
              <el-button type="primary" plain size="mini" round 
              @click="handleEdit(scope.$index, scope.row)">
              菜单配置
              </el-button>
              <el-button type="primary" plain size="mini" round
              @click="handPermission(scope.$index, scope.row)">
              权限配置
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 分配菜单 -->
    <div>
      <el-dialog title="分配菜单" :visible.sync="dialogVisibleMenu" width="30%">
        <el-tree :data="menu" :props="mdefaultProps" show-checkbox :expand-on-click-node="false" default-expand-all node-key="mid" :default-checked-keys="menucheckedkeys" ref="menutree">
        </el-tree>
        <el-button
          @click="dialogVisibleMenu = false;menucheckedkeys = [];">取 消</el-button>
        <el-button type="primary" @click="allocationMenu">确 定</el-button>
      </el-dialog>
    </div>
    <!-- 分配权限 -->
    <div>
      <el-dialog title="分配权限" :visible.sync="dialogVisiblePermiss" width="30%">
        <el-tree :data="permission" :props="pdefaultProps" show-checkbox :expand-on-click-node="false" default-expand-all node-key="pid" :default-checked-keys="permisscheckedkeys" ref="permissiontree">
        </el-tree>
        <el-button @click="dialogVisiblePermiss = false;permisscheckedkeys = [];">
            取 消
        </el-button>
        <el-button type="primary" @click="allocationPermission">
            确 定
        </el-button>
      </el-dialog>
    </div>
	  <!-- 添加Dialog弹框 -->
    <div>
      <el-dialog :title="myTitle" :visible.sync="dialogFormVisible" @close="closeDialog" width="25%">
        <el-form ref="form" :model="role" label-width="80px">
          <el-form-item label="姓名">
            <el-input v-model="role.rname" clearable ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="doRoleAdd">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
    data(){
      return{
        roles:[],
        role:{},
        dialogVisibleMenu: false,
        dialogVisiblePermiss: false,
        dialogFormVisible:false,
        menu: [],
        permission: [],
        mdefaultProps: {
          children: "children",
          label: "mname",
        },
        pdefaultProps:{
          children: "children",
          label: "pname",
        },
        menucheckedkeys: [],
        permisscheckedkeys: [],
        myTitle:"",
      };
    },
    created(){
      this.getAllRoles();
      this.getAllMenus();
      this.getAllPermissions();
    },
    methods:{
      addRole(){
        this.dialogFormVisible=true;
        this.myTitle ='添加角色'; 
      },
      //添加角色
      doRoleAdd(){
        if(this.myTitle=='添加角色'){
            this.$axios
                .post("http://localhost:8080/role/add", this.role)
                .then((resp) => {
            this.$message("添加成功:"+resp.data.data);
            this.dialogFormVisible = false;
            this.getAllRoles();//添加成功，重新查询，页面重新加载
          });
        }
      },
      //Dialog关闭的回调
      closeDialog(){
        this.role={};
      },
      //所有菜单
      getAllMenus(){
          this.$axios.get("http://localhost:8080/menu/get/all")
          .then((resp) => {
              if (resp.data.code === 200) {
                  this.menu = resp.data.data;
              }
          });
      },
      //所有角色
      getAllRoles(){
          this.$axios.get("http://localhost:8080/role/get/all")
          .then(resp=>{
              if (resp.data.code === 200) {
                  this.roles=resp.data.data;
              }                
          });
      },
      //所有权限
      getAllPermissions(){
          this.$axios.get("http://localhost:8080/permission/get/all")
          .then(resp=>{
              if (resp.data.code === 200) {
                  this.permission=resp.data.data;
              }                   
          });
      },
      //分配菜单事件
      handleEdit(index, row) {
          console.log("弹出分配菜单",index, row);
          this.dialogVisibleMenu=true;
          this.role={rid:row.rid};
          //获取已经分配过的菜单id-mids
          this.$axios.get("http://localhost:8080/role/getMids/"+row.rid)
          .then((resp) => {
              if (resp.data.code == 200) {
                  //所有的选中节点中，获取叶子结点
                  for(let mid of resp.data.data){
                      if(this.$refs.menutree.getNode(mid).isLeaf){
                          this.menucheckedkeys.push(mid);
                      }
                  }
                  this.$refs.menutree.setCheckedKeys(this.menucheckedkeys);
              }
          });
      },
      //菜单
      allocationMenu(){
          let rolemenu={role:this.role,mids:this.$refs.menutree.getCheckedKeys().concat(this.$refs.menutree.getHalfCheckedKeys())};
          this.$axios.post("http://localhost:8080/role/allocationMenu/",rolemenu)
          .then((resp) =>{
              if (resp.data.code == 200) {
                  this.$message("添加成功");
                  this.dialogVisibleMenu=false;
                  this.menucheckedkeys=[];
              }else {
                  this.$message(resp.data.msg);
              }
          });
      },
      //分配权限事件
      handPermission(index, row){               
          console.log("弹出分配权限",index, row);
          this.dialogVisiblePermiss=true;
          this.role={rid:row.rid};
          //获取已经分配过的权限id-pids
          this.$axios.get("http://localhost:8080/role/getPids/"+row.rid)
          .then((resp) => {
              if (resp.data.code == 200) {
                  //所有的选中节点中，获取叶子结点
                  for(let pid of resp.data.data){
                      if(this.$refs.permissiontree.getNode(pid).isLeaf){
                          this.permisscheckedkeys.push(pid);
                      }
                  }
                  this.$refs.permissiontree.setCheckedKeys(this.permisscheckedkeys);
              }
          });
      },
      //执行
      allocationPermission(){
          let rolepermission={role:this.role,pids:this.$refs.permissiontree.getCheckedKeys().concat(this.$refs.permissiontree.getHalfCheckedKeys())};
          this.$axios.post("http://localhost:8080/role/allocationPermission/",rolepermission)
          .then((resp) =>{
              if (resp.data.code == 200) {
                  this.$message("添加成功");
                  this.dialogVisiblePermiss=false;
                  this.permisscheckedkeys=[];
              }else {
                  this.$message(resp.data.msg);
              }
          });
      },
    },
};
</script>

<style>
.operation-column {
  display: flex;
  justify-content: center;
}
.operation-column button {
  margin-right: 5px;
}
</style>