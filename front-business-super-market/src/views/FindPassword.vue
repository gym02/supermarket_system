<template>
  <div>
    <div class="videocontainer">
      <video
        class="fullscreenvideo"
        playsinline=""
        autoplay=""
        muted=""
        loop=""
      >
        <source src="../imgs/girl.mp4" type="video/mp4" />
      </video>
    </div>

    <div class="login-box">
      <h2>登录系统</h2>
      <form>
        <div class="user-box">
          <el-form>
            <el-row>
              <el-col :span="12">
                <input type="text" name="" required="" v-model="user.email" />
                <label>邮箱</label>
              </el-col>
              <el-col :span="12">
                <el-button
                type="primary"
                  @click="send()"
                  style="
                    margin: 5px;
                    padding: 5px;
                    display: inline-block;
                    width: 120px;
                    height: 30px;
                  "
                  :disabled="controllDisabled"
                >
                  {{ msg }}
                </el-button>
              </el-col>
            </el-row>
          </el-form>
        </div>
        <div class="user-box">
          <input type="text" name="" required="" v-model="user.code" />
          <label>验证码</label>
        </div>
        <div class="user-box">
          <input type="password" name="" required="" v-model="firstPassword" />
          <label>修改密码</label>
        </div>
        <div class="user-box">
          <input
            type="password"
            name=""
            required=""
            v-model="secondPassword"
            @change="validPassword"
          />
          <label>确认密码</label>
        </div>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a @click="confirm()"> 确认 </a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </form>
    </div>
  </div>
</template>
  
  <script>
export default {
  data() {
    return {
      code: "",
      encode: "",
      uuid: "",
      msg: "发送验证码到邮箱",
      seconds: 10,
      controllDisabled: false,
      user: {
        email: "",
        code: "",
        username: "",
        password: "",
      },
      firstPassword: "",
      secondPassword: "",
      
    };
  },
  methods: {
    count(){
        this.msg = this.seconds + "秒后，重新发送";
        this.seconds--;  
      if (this.seconds == 0) {
        clearInterval(this.timer);
        this.controllDisabled = false;
        this.msg="发送验证码到邮箱"
        this.seconds = 10;
      } 
    },
    send(){
     
      this.controllDisabled = true;
      this.timer=setInterval(this.count,1000);
      this.$axios
        .post("http://localhost:8080/email/" + this.user.email)
        .then((resp) => {
          if (resp.data.code == 200) {
            this.$message({
              type: "success",
              message: "发送成功，注意查收",
            });
          }
        });
    },
    validPassword() {
      if (!(this.firstPassword == this.secondPassword)) {
        this.$message({
          type: "error",
          message: "两次密码不一致！",
        });
      }
    },
    confirm() {
      this.user.password = this.firstPassword;
      this.$axios
        .post("http://localhost:8080/changePassword", this.user)
        .then((resp) => {
          if (resp.data.code == 200) {
            this.$message({
              type: "success",
              message: "修改成功，请重新登录",
            });
            this.$router.push("/login");
          } else {
          this.$message("修改失败" + JSON.stringify(resp.data.data));
          }
        });
    },
  },
};
</script>
  
  <style>
.fullscreenvideo {
  position: absolute;
  top: 50%;
  left: 50%;
  min-width: 100%;
  min-height: 100%;
  width: 50%;
  height: 50%;
  z-index: -100;
  -webkit-transform: translateX(-50%) translateY(-50%);
  transform: translateX(-50%) translateY(-50%);
  -webkit-transition: 1s opacity;
  transition: 1s opacity;
  object-fit: cover;
}

.videocontainer {
  position: fixed;
  width: 100%;
  height: 100%;
  overflow: hidden;
  z-index: -100;
}

.videocontainer:before {
  content: "";
  position: absolute;
  width: 100%;
  height: 100%;
  display: block;
  z-index: -1;
  top: 0;
  left: 0;
  background: rgba(0, 0, 0, 0);
}

.login-box {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 400px;
  padding: 40px;
  transform: translate(-50%, -50%);
  background: rgba(0, 0, 0, 0.5);
  box-sizing: border-box;
  box-shadow: 0 15px 25px rgba(0, 0, 0, 0.6);
  border-radius: 10px;
}

.login-box h2 {
  margin: 0 0 30px;
  padding: 0;
  color: #fff;
  text-align: center;
}

.login-box .user-box {
  position: relative;
}

.login-box .user-box input {
  width: 100%;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  margin-bottom: 30px;
  border: none;
  border-bottom: 1px solid #fff;
  outline: none;
  background: transparent;
}

.login-box .user-box label {
  position: absolute;
  top: 0;
  left: 0;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  pointer-events: none;
  transition: 0.5s;
}

.login-box .user-box input:focus ~ label,
.login-box .user-box input:valid ~ label {
  top: -20px;
  left: 0;
  color: #03e9f4;
  font-size: 12px;
}

.login-box form a {
  position: relative;
  display: inline-block;
  padding: 10px 20px;
  color: #03e9f4;
  font-size: 16px;
  text-decoration: none;
  text-transform: uppercase;
  overflow: hidden;
  transition: 0.5s;
  margin-top: 40px;
  letter-spacing: 4px;
}

.login-box a:hover {
  background: #03e9f4;
  color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 5px #03e9f4, 0 0 25px #03e9f4, 0 0 50px #03e9f4,
    0 0 100px #03e9f4;
}

.login-box a span {
  position: absolute;
  display: block;
}

.login-box a span:nth-child(1) {
  top: 0;
  left: -100%;
  width: 100%;
  height: 2px;
  background: linear-gradient(90deg, transparent, #03e9f4);
  animation: btn-anim1 1s linear infinite;
}

@keyframes btn-anim1 {
  0% {
    left: -100%;
  }
  50%,
  100% {
    left: 100%;
  }
}

.login-box a span:nth-child(2) {
  top: -100%;
  right: 0;
  width: 2px;
  height: 100%;
  background: linear-gradient(180deg, transparent, #03e9f4);
  animation: btn-anim2 1s linear infinite;
  animation-delay: 0.25s;
}

@keyframes btn-anim2 {
  0% {
    top: -100%;
  }
  50%,
  100% {
    top: 100%;
  }
}

.login-box a span:nth-child(3) {
  bottom: 0;
  right: -100%;
  width: 100%;
  height: 2px;
  background: linear-gradient(270deg, transparent, #03e9f4);
  animation: btn-anim3 1s linear infinite;
  animation-delay: 0.5s;
}

@keyframes btn-anim3 {
  0% {
    right: -100%;
  }
  50%,
  100% {
    right: 100%;
  }
}

.login-box a span:nth-child(4) {
  bottom: -100%;
  left: 0;
  width: 2px;
  height: 100%;
  background: linear-gradient(360deg, transparent, #03e9f4);
  animation: btn-anim4 1s linear infinite;
  animation-delay: 0.75s;
}

@keyframes btn-anim4 {
  0% {
    bottom: -100%;
  }
  50%,
  100% {
    bottom: 100%;
  }
}
</style>