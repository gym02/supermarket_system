<template>
  <div>
    <!--  添加父级权限弹窗-->
    <div>
      <el-button type="primary" @click="parentlevel" circle plain>添加</el-button>
    </div>
    <div>
      <!-- <el-dialog :title="myTitle" :visible.sync="fdialogFormVisible" @close="closeDialog">
        <el-form ref="from" :model="permissions" label-width="80px">
          <el-form-item label="父权限名称">
            <el-input v-model="permissions.pname" clearable ></el-input>
          </el-form-item>
        </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="fdialogFormVisible = false" plain>取 消</el-button>
            <el-button type="primary" @click="addPpermission" plain>确 定</el-button>
          </div>
      </el-dialog> -->
      <el-dialog :title="myTitle" :visible.sync="fdialogFormVisible" width="25%" center :centered="true" @close="closeDialog" >
        <el-form ref="from" :model="permissions" label-width="70px">
          <el-form-item label="名称">
            <el-input v-model="permissions.pname" clearable ></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="fdialogFormVisible = false" plain>取 消</el-button>
          <el-button type="primary" @click="addPpermission" plain>确 定</el-button>
        </span>
      </el-dialog>
    </div>
    <!-- 添加子权限弹窗 -->
    <div>
      <el-dialog :title="myTitle" :visible.sync="dialogFormVisible" @close="closeDialog" width="28%">
        <el-form ref="from" :model="permissions" label-width="80px">
          <el-form-item label="名称">
            <el-input v-model="permissions.pname" clearable ></el-input>
          </el-form-item>
          <el-form-item label="路径">
            <el-input v-model="permissions.ppath" clearable ></el-input>
          </el-form-item>
          <el-form-item label="父权限编号">
            <el-input v-model="permissions.ppid" clearable disabled></el-input>
          </el-form-item>
        </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="permissionAdd">确 定</el-button>
          </div>
      </el-dialog>
    </div>
    <div>
      <el-tree :data="permission" :props="pdefaultProps" show-checkbox
        :expand-on-click-node="false" :default-expanded-keys="defaultexpandedkeys"
        node-key="pid">
        <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
          <span>
            <!-- <el-button v-if="data.children.length !== 0" type="text" size="mini" @click="() => append(data)">
            添加
            </el-button> -->
            <el-button v-if="data.ppid == 0" type="text" size="mini" @click="() => append(data)">
            添加
            </el-button>
            <el-button v-if="data.ppid != 0" type="text" size="mini" 
            @click="() => remove(node, data)">
            删除
            </el-button>
            <!-- <el-button v-if="data.children.length === 0" type="text" size="mini" 
            @click="() => remove(node, data)">
            删除
            </el-button> -->
          </span>
        </span>
      </el-tree>
    </div>
  </div>
</template>

<script>
  export default {
    data(){
      return{
          permission:[],
          permissions:{},
          pdefaultProps: {
              children: "children",
              label: "pname",
          },
          defaultexpandedkeys:[],
          myTitle:"",
          dialogFormVisible:false,
          fdialogFormVisible:false,
      };
    },
    created() {
        this.getAllPermissions();
    },
    methods:{
        //Dialog关闭的回调
        closeDialog(){
            this.permissions={};
        },
        //查询全部权限
        getAllPermissions() {
            this.$axios.get("http://localhost:8080/permission/get/all")
            .then((resp) => {
            if (resp.data.code === 200) {
                this.permission = resp.data.data;
                // console.log(this.permission);
            }
            });
        },
        //确定
        permissionAdd(){
          if(this.myTitle ='添加子权限'){
            this.$axios.post("http://localhost:8080/permission/addPermissions",this.permissions)
              .then((resp) => {
              if(resp.data.code === 200){
                  this.$message("子权限添加成功");
                  this.dialogFormVisible = false;
                  //添加成功，重新查询，页面重新加载
                  this.getAllPermissions();
              }else{
                  this.$message(resp.data.data);
              }
            });
          }         
        },
        addPpermission(){
          if(this.myTitle=='添加父权限'){
            this.$axios.post("http://localhost:8080/permission/addParentPermission",this.permissions)
              .then((resp) => {
              if(resp.data.code === 200){
                  this.$message("父权限添加成功");
                  this.fdialogFormVisible = false;
                  //添加成功，重新查询，页面重新加载
                  this.getAllPermissions();
              }else{
                  this.$message(resp.data.data);
              }
          });
          }         
        },
        parentlevel(){
          this.fdialogFormVisible=true;
          this.myTitle ='添加父权限'; 
        },
        append(data) {
          this.dialogFormVisible=true;
          this.myTitle ='添加子权限'; 
          this.permissions.ppid = data.pid;
        },
        remove(node, data) {
          console.log("remove",node,data);
          this.$axios.get("http://localhost:8080/permission/remove/"+data.pid)
          .then((resp) => {
              if (resp.data.code === 200) {
                  this.$message('删除成功');
                  //刷新权限
                  this.getAllPermissions();
                  this.defaultexpandedkeys.push(node.parent.data.pid);
              }else{
                  this.$message(resp.data.data);
              }
          });
        },
    }
  }
</script>

<style>

</style>