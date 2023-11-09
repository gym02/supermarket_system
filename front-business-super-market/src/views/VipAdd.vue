<template>
  <div>
    <el-card shadow="always" style="margin-bottom: 10px" >
      <span style="font-weight:bold;"> 新增会员 </span>
      <el-form
        :model="vipAdd"
        :rules="rules"
        :ref="vipAdd"
        label-width="100px"
        class="demo-ruleForm"
        status-icon
        style="margin-left:430px;margin-top:20px"
      >
        <el-row>
          <el-col :span="10">
            <el-form-item label="会员姓名" prop="hyName">
              <el-input v-model="vipAdd.hyName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="10">
            <el-form-item label="密码" prop="hyPassword"  >
              <el-input v-model="vipAdd.hyPassword" type="password" autocomplete="off"  clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="10">
            <el-form-item label="会员邮箱" prop="gkEmail">
              <el-input v-model="vipAdd.gkEmail"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="10">
            <el-form-item label="余额" prop="hyBalance">
              <el-input v-model.number="vipAdd.hyBalance"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item style="text-align: left">
          <button
            type="button"
            @click="submitForm()"
            class="css-button-rounded--green"
          >
            添加
          </button>
          <button
            type="button"
            @click="resetForm('vipAdd')"
            class="css-button-rounded--green"
          >
            重置
          </button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
      <span>注册成功！会员卡号为{{ this.hyId }}</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>

export default {
  data() {
    // 检验输入余额数据合法性
    var checkBalance = (rule, value, callback) => {
      if (value == 0) {
      } else {
        if (!value) {
          return callback(new Error("请输入初始余额"));
        }
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error("请输入数字值"));
        } else {
          if (value < 0) {
            callback(new Error("余额不能小于0"));
          } else {
            callback();
          }
        }
      }, 1000);
    };

    return {
      dialogVisible: false,
      hyId: "",
      vipAdd: {
        hyName: "",
        hyUsername: "",
        hyPassword: '',
        gkEmail: "",
        hyBalance: "",
      },

      rules: {
        hyName: [
          { required: true, message: "请输入会员名字", trigger: "blur" },
          { min: 2, max: 4, message: "长度在 2 到 4 个字符", trigger: "blur" },
        ],
        hyPassword: [
          { required: true, message: "请输入会员密码", trigger: "blur" },
          { min: 6, message: "长度不小于 6 个字符", trigger: "blur" },
        ],
        gkEmail: [
          { required: true, message: "请输入邮箱地址", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],
        hyBalance: [
          { required: true, validator: checkBalance, trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    submitForm() {
      try {
        if (this.vipAdd.hyPassword != '' && this.vipAdd.hyPassword != null) {
          this.vipAdd.hyPassword = this.$md5(this.$md5(this.vipAdd.hyPassword));
        }
        this.$axios
          .post("http://127.0.0.1:8080/vip/addVip", this.vipAdd)
          .then((resp) => {
            if (resp.data.code == 200) {
              this.hyId = resp.data.data;
              this.dialogVisible = true;
              this.vipAdd = {
                name: "",
                username: "",
                password: "",
                email: "",
                balance: "",
              };
            } else if (resp.data.code == 400) {
              this.$message.error("会员信息不存在");
            } else if(resp.data.code == 405){
              this.$message.error("该邮箱已被注册");
            }
            else {
              this.$message.error("输入数据非法");
            }
          });
      } catch (error) {
        this.$message({
          type: "error",
          message: "输入数据非法",
        });
      }
    },
    resetForm(vipAdd) {
      this.vipAdd = {
        name: "",
        username: "",
        password: "",
        email: "",
        balance: "",
      };
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
</style>

