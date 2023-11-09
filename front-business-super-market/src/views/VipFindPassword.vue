<template>
  <div>
    <el-card shadow="always" style="margin-bottom: 10px">
      <!-- 进度条 -->
      <el-steps :active="step" finish-status="success" simple>
        <el-step title="身份验证"> ></el-step>
        <el-step title="修改密码"></el-step>
        <el-step title="修改成功"></el-step>
      </el-steps>

      <!-- 身份验证 -->
      <el-form
        v-if="formStep == 1"
        style="margin-top: 30px; margin-left: 420px"
        :model="vipFindPasswordDto"
        :rules="rules"
        :ref="vipFindPasswordDto"
        label-width="100px"
        class="demo-ruleForm"
        status-icon
      >
        <el-row>
          <el-col :span="10">
            <el-form-item label="会员姓名" prop="hyName">
              <el-input v-model="vipFindPasswordDto.hyName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="10">
            <el-form-item label="会员邮箱" prop="gkEmail">
              <el-input v-model="vipFindPasswordDto.gkEmail"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="1">
            <el-button
              @click="send()"
              style="margin-left: 15px; width: 150px; height: 40px"
              type="primary"
              class="css-button-rounded--green"
              :disabled="controllDisabled"
            >
              {{ msg }}
            </el-button>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="10">
            <el-form-item label="验证码" prop="code">
              <el-input v-model="vipFindPasswordDto.code"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item style="text-align: left">
          <button
            type="button"
            class="css-button-rounded--green"
            @click="findPassword()"
          >
            提交
          </button>
          <button
            type="button"
            class="css-button-rounded--green"
            @click="reset('vipFindPasswordDto')"
          >
            重置
          </button>
        </el-form-item>
      </el-form>

      <!-- 修改密码 -->
      <el-form
        v-if="formStep == 2"
        :model="vipUpdatePasswordDto"
        status-icon
        :rules="rules"
        ref="vipUpdatePasswordDto"
        label-width="100px"
        class="demo-ruleForm"
        style="margin-top: 30px; margin-left: 420px"
      >
        <el-row>
          <el-col :span="10">
            <el-form-item label="会员邮箱" prop="gkEmail">
              <el-input
                disabled
                v-model="vipUpdatePasswordDto.gkEmail"
                autocomplete="off"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="10">
            <el-form-item label="密码" prop="pass">
              <el-input
                type="password"
                v-model="vipUpdatePasswordDto.pass"
                autocomplete="off"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="10">
            <el-form-item label="确认密码" prop="checkPass">
              <el-input
                type="password"
                v-model="vipUpdatePasswordDto.checkPass"
                autocomplete="off"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item style="text-align: left">
          <button
            type="button"
            class="css-button-rounded--green"
            @click="findPassword2('vipUpdatePasswordDto')"
          >
            提交
          </button>
          <button
            type="button"
            class="css-button-rounded--green"
            @click="reset2('vipUpdatePasswordDto')"
          >
            重置
          </button>
        </el-form-item>
      </el-form>

      <!-- 修改成功 -->
      <div style="margin-left: 40%">
        <el-row v-if="formStep == 3">
          <el-col :sm="12" :lg="6">
            <el-result
              icon="success"
              title="修改成功"
              subTitle="请妥善保管会员密码"
            >
              <template slot="extra">
                <button
                  type="button"
                  class="css-button-rounded--green"
                  size="medium"
                  @click="back"
                >
                  返回
                </button>
              </template>
            </el-result>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.vipUpdatePasswordDto.checkPass !== "") {
          this.$refs.vipUpdatePasswordDto.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.vipUpdatePasswordDto.pass) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      // 控制进度条
      step: 1,
      // 控制表格显示
      formStep: 1,

      vipFindPasswordDto: {
        hyName: "",
        gkEmail: "",
        code: "",
      },
      vipUpdatePasswordDto: {
        gkEmail: "",
        pass: "",
        checkPass: "",
      },
      msg: "发送验证码到邮箱",
      seconds: 30,
      controllDisabled: false,
      rules: {
        hyName: [
          { required: true, message: "请输入会员名字", trigger: "blur" },
          { min: 2, max: 4, message: "长度在 2 到 4 个字符", trigger: "blur" },
        ],
        gkEmail: [
          { required: true, message: "请输入邮箱地址", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],
        pass: [
          { validator: validatePass, trigger: "blur" },
          { min: 6, message: "长度不小于 6 个字符", trigger: "blur" },
        ],
        checkPass: [{ validator: validatePass2, trigger: "blur" }],
      },
    };
  },
  created() {
    this.vipFindPasswordDto.hyName = this.$route.params.hyName;
    this.vipFindPasswordDto.gkEmail = this.$route.params.gkEmail;
  },
  methods: {
    count() {
      this.msg = this.seconds + "秒后，重新发送";
      this.seconds--;
      if (this.seconds == -1) {
        clearInterval(this.timer);
        this.controllDisabled = false;
        this.msg = "发送验证码到邮箱";
        this.seconds = 30;
      }
    },
    send() {
      this.msg = this.seconds + "秒后，重新发送";
      this.seconds--;
      this.controllDisabled = true;
      this.timer = setInterval(this.count, 1000);
      this.$axios
        .get(
          "http://127.0.0.1:8080/vip/email?email=" +
            this.vipFindPasswordDto.gkEmail
        )
        .then((resp) => {
          if (resp.data.code == 200) {
            this.$message.success("发送成功，注意查收");
          }
        });
    },
    findPassword() {
      try {
        this.$axios
          .post(
            "http://127.0.0.1:8080/vip/findPassword",
            this.vipFindPasswordDto
          )
          .then((resp) => {
            if (resp.data.code == 200) {
              this.$message.success("验证通过");
              this.vipUpdatePasswordDto.gkEmail = resp.data.data;
              this.step = 2;
              this.formStep = 2;
              this.vipFindPasswordDto.hyName = "";
              this.vipFindPasswordDto.gkEmail = "";
              this.vipFindPasswordDto.hyPassword = "";
              this.vipFindPasswordDto.code = "";
            } else if (resp.data.code == 400) {
              this.$message.error("会员信息不存在");
            } else if (resp.data.code == 402) {
              this.$message.error("验证码错误");
            } else {
              this.$message.error("输入数据非法");
            }
          });
      } catch (error) {
        this.$message.error("输入数据非法");
      }
    },
    reset(vipFindPasswordDto) {
      this.$refs[vipFindPasswordDto].resetFields();
    },
    findPassword2() {
      try {
        this.vipUpdatePasswordDto.pass = this.$md5(
          this.$md5(this.vipUpdatePasswordDto.pass)
        );
        this.vipUpdatePasswordDto.checkPass = this.$md5(
          this.$md5(this.vipUpdatePasswordDto.checkPass)
        );
        this.$axios
          .post(
            "http://127.0.0.1:8080/vip/findPassword2",
            this.vipUpdatePasswordDto
          )
          .then((resp) => {
            if (resp.data.code == 200) {
              this.step = 3;
              this.formStep = 3;
              this.vipUpdatePasswordDto.gkEmail = "";
              this.vipUpdatePasswordDto.hyPassword = "";
              this.vipUpdatePasswordDto.hyCheckPassword = "";
            } else if (resp.data.code == 400) {
              this.$message.error("两次密码输入不一致");
            }
          });
      } catch {
        this.$message.error("输入数据不合法");
      }
    },
    reset2(vipUpdatePasswordDto) {
      this.vipUpdatePasswordDto.pass = "";
      this.vipUpdatePasswordDto.checkPass = "";
    },
    back() {
      this.step = 1;
      this.formStep = 1;
    },
  },
};
</script>

<style>
</style>