<template>
  <div>
    <!--  添加父级菜单弹窗-->
    <div>
      <el-button type="primary" @click="parentlevel" plain circle>添加</el-button>
    </div>
    <div>
      <!-- <el-dialog :title="myTitle" :visible.sync="fdialogFormVisible" @close="closeDialog">
        <el-form ref="from" :model="menus" label-width="80px">
          <el-form-item label="父菜单名">
            <el-input v-model="menus.mname" clearable ></el-input>
          </el-form-item>
        </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="fdialogFormVisible = false" plain>取 消</el-button>
            <el-button type="primary" @click="addPmenu" plain>确 定</el-button>
          </div>
      </el-dialog> -->
      <el-dialog :title="myTitle" :visible.sync="fdialogFormVisible" width="27%" center :centered="true" @close="closeDialog">
        <el-form ref="from" :model="menus" label-width="70px">
          <el-form-item label="名称">
            <el-input v-model="menus.mname" clearable ></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="fdialogFormVisible = false" plain>取 消</el-button>
          <el-button type="primary" @click="addPmenu" plain>确 定</el-button>
        </span>
      </el-dialog>
    </div>
    <!-- 添加子菜单弹窗 -->
    <div>
      <el-dialog :title="myTitle" :visible.sync="dialogFormVisible"
        @close="closeDialog" width="28%">
        <el-form ref="from" :model="menus" label-width="100px">
          <el-form-item label="名称">
            <el-input v-model="menus.mname" clearable></el-input>
          </el-form-item>
          <el-form-item label="路径">
            <el-input v-model="menus.mpath" clearable></el-input>
          </el-form-item>
          <el-form-item label="父菜单编号">
            <el-input v-model="menus.pmid" clearable disabled></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="menuAdd">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div>
      <el-tree :data="menu" :props="mdefaultProps" show-checkbox :expand-on-click-node="false" :default-expanded-keys="defaultexpandedkeys" node-key="mid">
        <span class="custom-tree-node" slot-scope="{ node, data }">
          <span>{{ node.label }}</span>
          <span>
            <el-button v-if="data.pmid == 0 || data.pmid == data.mid" type="text" size="mini" @click="() => append(data)">
            添加
            </el-button>
            <el-button v-if="data.pmid != 0" type="text" size="mini" 
            @click="() => remove(node, data)">
            删除
            </el-button>
          </span>
        </span>
      </el-tree>
    </div>
  </div>
</template>

<script>
export default {
    data(){
      return {
        menu: [],
        menus: {},
        mdefaultProps: {
            children: "children",
            label: "mname",
        },
        defaultexpandedkeys:[],
        myTitle:"",
        dialogFormVisible:false,
        fdialogFormVisible:false,
      };
    },
    created(){
      this.getAllMenus();
    },
    methods: {
      //Dialog关闭的回调
      closeDialog(){
        this.menus={};
      },
      getAllMenus() {
        this.$axios.get("http://localhost:8080/menu/get/all")
        .then((resp) => {
          if (resp.data.code === 200) {
            this.menu = resp.data.data;
            console.log(this.menus);
          }
        });
      },
      menuAdd(){
        if(this.myTitle ='添加子菜单'){
          this.$axios.post("http://localhost:8080/menu/addMenus",this.menus)
          .then((resp) => {
            if(resp.data.code === 200){
                this.$message("子菜单添加成功");         
                this.dialogFormVisible = false;
                //添加成功,重新查询,刷新页面
                this.getAllMenus();
            }else{
              this.$message(resp.data.data);
            }
          }); 
        }              
      },
      addPmenu(){
        if(this.myTitle ='添加父菜单'){
          this.$axios.post("http://localhost:8080/menu/addParentMenus",this.menus)
          .then((resp) => {
            if(resp.data.code === 200){
                this.$message("父菜单添加成功");         
                this.fdialogFormVisible = false;
                //添加成功,重新查询,刷新页面
                this.getAllMenus();
            }else{
              this.$message(resp.data.data);
            }
          });
        }       
      },
      parentlevel(data) {
        this.fdialogFormVisible=true;
        this.myTitle ='添加父菜单'; 
      },
      append(data) {
        this.dialogFormVisible=true;
        this.myTitle ='添加子菜单'; 
        this.menus.pmid = data.mid;
      },
      remove(node, data) {
        console.log("remove",node,data);
        this.$axios.get("http://localhost:8080/menu/remove/"+data.mid)
        .then((resp) => {
            if (resp.data.code === 200) {
              this.$message('删除成功');
              //刷新菜单
              this.getAllMenus();
              this.defaultexpandedkeys.push(node.parent.data.mid);
            }else{
              this.$message(resp.data.data);
            }
        });
      },
    },
};
</script>

<style>
</style>