<template>
  <div>
    <!--修改或添加用户对话框 -->
    <div>
      <el-dialog :title="myTitle" :visible.sync="dialogFormVisible"
        @close="closeDialog" width="25%">      
        <el-form ref="user" :model="userModify" label-width="80px" :rules="rules">
          <el-form-item label="姓名" prop="userName">
            <el-input v-model="userModify.userName" clearable></el-input>            
          </el-form-item>
          <el-form-item label="密码" prop="userPwd" type="password" v-if="myTitle === '添加用户'">
            <el-input v-model="userModify.userPwd"></el-input>            
          </el-form-item>
          <el-form-item label="邮箱" prop="userEmail">
            <el-input v-model="userModify.userEmail" clearable></el-input>           
          </el-form-item>
          <el-form-item label="所属角色">
            <el-select v-model="userModify.role.rid" clearable>
              <el-option v-for="r in roles" :key="r.rid" 
              :label="r.rname" :value="r.rid"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="addOrModifyUser('user')">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <!-- 多条件查询 -->
    <div>
      <el-form ref="form" :model="user" label-width="120px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="姓名" style="width: 300px">
              <el-input v-model="user.userName" placeholder="请输入内容" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="角色" style="width: 300px">
              <el-select v-model="user.role.rid" clearable placeholder="请选择">
                <el-option v-for="r in roles" :key="r.rid" 
                :label="r.rname" :value="r.rid"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-button type="primary" plain @click="getUsers" size="mini">
                查询
            </el-button>
            <el-button type="primary" plain @click="showEditDialog" size="mini">
                添加
            </el-button>
            <el-button type="primary" plain @click="removeBatchUser" size="mini">
                批量删除
            </el-button>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <!-- 员工列表 -->
    <div>
      <el-table :data="pageBean.beanList" style="width: 100%" :row-class-name="tableRowClassName"
        @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="userId" label="用户编号" sortable></el-table-column>
        <el-table-column prop="userName" label="姓名" sortable></el-table-column>
        <el-table-column prop="userEmail" label="邮箱"></el-table-column>
        <el-table-column prop="role.rname" label="所属角色" sortable></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-delete" circle 
            @click="handleDelete(scope.$index, scope.row)" plain>
            </el-button>
            <el-button type="primary" icon="el-icon-edit" circle 
            @click="handleEdit(scope.$index, scope.row)" plain>
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 分页查询 -->
    <div>
      <el-pagination
        @size-change="changeSize"
        @current-change="changeNum"
        :current-page="pageBean.pageNum"
        :page-sizes="[5, 10, 15]"
        :page-size="pageBean.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pageBean.totalRecord">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    var checkEmail = (rule, value, callback) => {
      if (typeof value == "undefined" || value == "") {
        callback(new Error("邮箱名不能为空"));
      } else if (!/^\w+@\w+\.\w+$/.test(value)) {
        callback(new Error("邮箱格式错误"));
      } else {
        this.$axios.post("http://localhost:8080/user/findEmail/" + value)
        .then((resp) => {
          if (resp.data.code == 200) {
            callback();
          } else if(resp.data.code==400){
            callback(new Error("邮箱已经注册"));
          }
        });       
      }
    };
    var checkName = (rule, value, callback) => {
      if (typeof value == "undefined" || value == "") {
        callback(new Error("用户名不能为空"));
      } else if (value.length < 3 || value.length > 6) {
        callback(new Error("用户名长度错误"));
      } else {
        this.$axios.post("http://localhost:8080/user/findUserName/" + value)
        .then((resp) => {
          if (resp.data.code == 200) {
            callback();
          } else if(resp.data.code==400){
            callback(new Error("用户名已经使用"));
          }
        });       
      }
    };
    return {
      multipleSelection: [],
      myTitle: "", // 弹窗标题
      dialogFormVisible: false,
      pageNum: 1,
      pageSize: 5,
      pageBean: {}, // 查询条件
      // 用户列表数据
      user: {
        role: {
          rid: null,
        },
      },
      // 修改或添加的用户信息
      userModify: {
        role: {
          rid: null,
        },
      },
      roles: [], // 角色列表数据
      // errorMsg: {}, // 错误信息 
      //具体规则
      rules: {
        userName: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 3, max: 6, message: "长度在 3 到 6 个字符", trigger: "blur" },
        ],
        userPwd: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 3, max: 6, message: "长度在 3 到 6 个字符", trigger: "blur" },
        ],
        userEmail: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          {
            pattern: /^\w+@\w+\.\w+$/,message: "邮箱格式错误",trigger: "blur",
          },
        ],
      },
    };  
  },
  created() {
    this.getUsers();
    this.getAllRoles();
  },
  methods: {
    //跳转新增/修改页面
    showEditDialog() {
      this.dialogFormVisible = true;
      this.myTitle = "添加用户";
    // this.errorMsg = {}; // 清空错误信息    
    },

    //当前页面执行编辑或添加
    addOrModifyUser(user) {
      this.$refs[user].validate((valid)=>{
        if(valid){
          if (this.myTitle == "添加用户") {
            // 加密密码
            // 生成随机盐值
            // const salt = Math.random().toString(36).substring(2, 10); 
            // 对密码加密和盐值拼接
            // const password = md5(this.userModify.userPwd + salt); 
            // 构造添加用户的请求参数
            // const requestData = { ...this.userModify, userPwd: password, salt: salt };            
            this.$axios
              .post("http://localhost:8080/user/add", this.userModify)
              .then((resp) => {
                if (resp.data.code === 400 && resp.data.data) {
                  this.$message({
                    message: resp.data.data,
                    type: "error",
                  });
                } else{
                  this.$message("添加成功");
                  this.dialogFormVisible = false;
                  this.getUsers(); //页面重新加载刷新
                }               
            });
          } else {
            this.$axios
              .post("http://localhost:8080/user/modify", this.userModify)
              .then((resp) => {
                if (resp.data.code === 400 && resp.data.data) {
                  this.$message({
                    message: resp.data.data,
                    type: "error",
                  });
                } else{
                  this.$message("修改成功");
                  this.dialogFormVisible = false;
                  this.getUsers();
                }               
            });
          }
        } else{
          // console.log("error submit!!");
          return false;
        }
      });
      // 验证前清空错误信息
      // this.errorMsg = {};
      // if (this.myTitle == "添加用户") {
      //   this.$axios
      //     .post("http://localhost:8080/user/add", this.userModify)
      //     .then((resp) => {
      //       if (resp.data.code === 400 && resp.data.data) {
      //         this.errorMsg = JSON.parse(resp.data.data.replace(/'/g, '"'));
      //         return;
      //       }
      //       this.$message("添加成功:" + resp.data.data);
      //       this.dialogFormVisible = false;
      //       this.getUsers(); //页面重新加载刷新
      //     });
      // } else {
      //   this.$axios
      //     .post("http://localhost:8080/user/modify", this.userModify)
      //     .then((resp) => {
      //       if (resp.data.code === 400 && resp.data.data) {
      //         this.errorMsg = JSON.parse(resp.data.data.replace(/'/g, '"'));
      //         return;
      //       }
      //       this.$message("修改成功:" + resp.data.data);
      //       this.dialogFormVisible = false;
      //       this.getUsers();
      //     });
      // }
    },
    //编辑事件
    handleEdit(index, row) {
      this.dialogFormVisible = true;
      this.myTitle = "修改用户";
      this.userModify = { ...row };
    },
    //删除事件
    handleDelete(index, row) {
      // console.log(index, row);
      this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          //发送请求，后端执行删除
          this.$axios
            .get("http://localhost:8080/user/remove/" + row.userId)
            .then((resp) => {
              if (resp.data.code == 200) {
                this.$message({
                  type: "success",
                  message: "删除成功!",
                });
              }
            });
        })
        .catch(() => {});
    },
    //批量删除事件
    removeBatchUser() {
      if (this.multipleSelection.length == 0) {
        this.$message("请先选择");
      } else {
        this.$confirm("此操作将永久删除该数据,是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
          .then(() => {
            //发送请求，后端执行删除
            let userIds = "?";
            // let userIds= [];
            for (let i = 0; i < this.multipleSelection.length; i++) {
              userIds += "userIds=" + this.multipleSelection[i].userId + "&";
            }
            this.$axios
              .post("http://localhost:8080/user/removeBatchUser" + userIds)
              .then((resp) => {
                if (resp.data.code == 200) {
                  this.$message({
                    type: "success",
                    message: "批量删除成功!",
                  });
                  this.getUsers();
                } else {
                  this.$message({
                    type: "error",
                    message: "未知异常",
                  });
                }
              });
          })
          .catch(() => {});
      }
    },
    //Dialog关闭对话框
    closeDialog() {
      this.userModify = { role: { rid: null } };
      this.errorMsg = {}; // 清空错误信息
    },
    //查询所有用户
    getUsers() {
      this.$axios
        .post(
          "http://localhost:8080/user/get/" + this.pageNum + "/" +
            this.pageSize,this.user)
        .then((resp) => {
          this.pageBean = resp.data.data;
          // console.log(resp.data.data);
        });
    },
    //查询所有角色
    getAllRoles() {
      this.$axios.get("http://localhost:8080/role/get/all").then((resp) => {
        this.roles = resp.data.data;
      });
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    //分页事件,页大小改变
    changeSize(val) {
      this.pageSize = val;
      this.getUsers();
    },
    //分页事件,当前页改变
    changeNum(val) {
      this.pageNum = val;
      this.getUsers();
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex % 2 === 0) {
        return "warning-row";
      } else {
        return "success-row";
      }
    },
    //验证邮箱是否已经被注册
    findEmail(email) {
      this.$axios
        .post("http://localhost:8080/user/findEmail/" + this.user.userEmail)
        .then((resp) => {
          if (resp.data.code != 200) {
            this.flag = false;
            this.$message({
              message: "邮箱已被注册",
              type: "warning",
            });
          } else {
            this.flag = true;
          }
        });
    }, 
    //验证用户名是否已经被注册
    findUserName(userName) {
      this.$axios
        .post("http://localhost:8080/user/findUserName/" + this.user.userName)
        .then((resp) => {
          if (resp.data.code != 200) {
            this.flag = false;
            this.$message({
              message: "用户名已被注册",
              type: "warning",
            });
          } else {
            this.flag = true;
          }
        });
    },
  },
};
</script>

<style>
</style>