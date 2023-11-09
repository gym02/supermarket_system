<template>
  <div>
    <!------------------------------ 商品数据搜索框 --------------------------------------------->
    <el-card shadow="always" style="margin-bottom: 10px;">
      <div style="margin-bottom: 5px">
        <el-form ref="form" size="small" label-width="200px" style="margin-top: 23px">
          <el-row :gutter="20">
            <el-col :span="6">
              <el-form-item label="商品名称">
                <el-input v-model="productDTO.spName"></el-input>
              </el-form-item>
              <el-form-item label="商品类别">
                <el-select v-model="productDTO.zlId" clearable placeholder="请选择">
                  <el-option
                    v-for="kind in kinds"
                    :key="kind.zlId"
                    :label="kind.zlName"
                    :value="kind.zlId"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="供货商">
                <el-select v-model="productDTO.suId" clearable placeholder="请选择">
                  <el-option
                    v-for="supply in supplies"
                    :key="supply.suId"
                    :label="supply.suName"
                    :value="supply.suId"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="最低单价">
                <el-input v-model="productDTO.minPrice"></el-input>
              </el-form-item>
              <el-form-item label="最高单价">
                <el-input v-model="productDTO.maxPrice"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="起始时间">
                <el-date-picker
                  v-model="productDTO.startDate"
                  type="date"
                  format="yyyy年MM月dd日"
                  value-format="yyyy年MM月dd日"
                  clearable
                  placeholder="选择时间"
                ></el-date-picker>
              </el-form-item>
              <el-form-item label="结束时间">
                <el-date-picker
                  v-model="productDTO.endDate"
                  type="date"
                  format="yyyy年MM月dd日"
                  value-format="yyyy年MM月dd日"
                  clearable
                  placeholder="选择时间"
                ></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20">
            <el-col :span="2" style="margin-left: 15px">
              <div>
                <button
                  type="button"
                  size="small"
                  @click="search()"
                  class="css-button-rounded--green"
                >
                  <i class="el-icon-search"></i>&nbsp;搜索
                </button>
              </div>
            </el-col>
            <el-col :span="1">
              <div>
                <button
                  type="success"
                  size="small"
                  @click="reset()"
                  class="css-button-rounded--green"
                >
                  <i class="el-icon-refresh"></i>&nbsp;重置
                </button>
              </div>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </el-card>
    <!-------------------------------------- 商品数据显示表 ------------------------------------>
   
    <el-card shadow="always">
      <div style="text-align: left; margin-bottom: 15px">
        <button
          type="primary"
          size="small"
          @click="dialogVisible = true"
          class="css-button-rounded--blue"
        >
          <i class="el-icon-plus"></i>&nbsp;添加
        </button>
        <button type="danger" size="small" @click="removeAll()" class="css-button-rounded--red">
          <i class="el-icon-delete"></i>&nbsp;删除
        </button>
        <button type="success" size="small" @click="exportExcel" class="css-button-rounded--export">
          <i class="el-icon-upload2"></i>&nbsp;导出
        </button>
        <span style="text-align: right; float: right; margin: 15px">
          <el-button size="small" plain type="info">
            <i class="el-icon-s-operation"></i>
          </el-button>
        </span>
      </div>
      <div>
        <el-table
          ref="multipleTable"
          :data="pageBean.beanList"
          tooltip-effect="dark"
          style="width: 100%"
          :default-sort="{ product: 'descending' }"
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
          <el-table-column type="selection" width="55" class="myRedCheckBox"></el-table-column>
          <el-table-column
            align="center"
            label="商品编号"
            width="200"
            show-overflow-tooltip
            sortable
            prop="spId"
            fixed
          ></el-table-column>
          <el-table-column
            align="center"
            label="商品名称"
            width="200"
            show-overflow-tooltip
            sortable
            prop="spName"
            fixed
          ></el-table-column>
          <el-table-column
            align="center"
            label="商品类别"
            width="200"
            show-overflow-tooltip
            sortable
            prop="zlName"
            fixed
          ></el-table-column>
          <el-table-column
            align="center"
            label="供货商"
            width="200"
            show-overflow-tooltip
            sortable
            prop="suName"
            fixed
          ></el-table-column>
          <el-table-column
            align="center"
            label="零售价"
            width="200"
            show-overflow-tooltip
            sortable
            prop="spPrice"
            fixed
          ></el-table-column>
           <el-table-column
            align="center"
            label="单位"
            width="200"
            show-overflow-tooltip
            sortable
            prop="unit"
            fixed
          ></el-table-column>
          <el-table-column
            align="center"
            label="生产日期"
            width="200"
            show-overflow-tooltip
            sortable
            prop="spProduceDate"
            fixed
          ></el-table-column>
          <el-table-column label="图片" width="280" show-overflow-tooltip sortable>
            <template slot-scope="scope">
              <img
                :src="'http://localhost:8080/images/'+scope.row.spImage"
                width="260px"
                height="200px"
              />
            </template>
          </el-table-column>

          <!-- <el-table-column label="商品状态" width="80">
            <template slot-scope="scope">
              <span v-if="scope.row.status==0">在库</span>
              <span v-if="scope.row.status==1">已下架</span>
              <span v-if="scope.row.status==2">补货中</span>
            </template>
          </el-table-column> -->
          <el-table-column label="操作" fixed="right" align="center">
            <template slot-scope="scope">
              <button
                size="small"
                plain
                type="warning"
                @click="showUpdate(scope.$index, scope.row)"
                class="css-button-rounded--yellow"
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
        <div>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[2, 4, 6]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pageBean.totalRecord"
          ></el-pagination>
        </div>
      </div>
    </el-card>

    <!-- 对话框 -->
    <el-dialog title="show" :visible.sync="dialogVisible">
      <el-form ref="product" :rules="rules" :model="product" label-width="80px">
        <el-form-item label="商品名称" prop="spName">
          <el-input v-model="product.spName"></el-input>
        </el-form-item>

        <el-form-item label="商品类别" >
          <el-select v-model="product.zlId" clearable placeholder="请选择">
            <el-option
              v-for="kind in kinds"
              :key="kind.zlId"
              :label="kind.zlName"
              :value="kind.zlId"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="供货商">
          <el-select v-model="product.suId" clearable placeholder="请选择">
            <el-option
              v-for="supply in supplies"
              :key="supply.suId"
              :label="supply.suName"
              :value="supply.suId"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="零售价" prop="spPrice">
          <el-input v-model="product.spPrice"></el-input>
        </el-form-item>
          <el-form-item label="计量单位">
          <el-input v-model="product.unit"></el-input>
        </el-form-item>
        <el-form-item label="图片" prop="spImage">
          <el-upload
            class="avatar-uploader"
            action="http://localhost:8080/shop/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img
              v-if="product.spImage"
              :src="'http://localhost:8080/images/'+product.spImage"
              class="avatar"
            />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>

        <el-form-item label="生产日期">
          <el-date-picker
            v-model="product.spProduceDate"
            type="date"
            format="yyyy年MM月dd日"
            value-format="yyyy年MM月dd日"
            clearable
            placeholder="选择时间"
          ></el-date-picker>
        </el-form-item>
        <!-- <el-form-item label="商品状态">
          <el-radio v-model="product.status" label="1">在库</el-radio>
          <el-radio v-model="product.status" label="2">上架中</el-radio>
          <el-radio v-model="product.status" label="3">补货中</el-radio>
          <el-radio v-model="product.status" label="4">已下架</el-radio>
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancle()">取 消</el-button>
        <el-button type="primary" @click="addOrUpdate('product')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //前端验证添加商品
       rules: {
        spName: [
          { required: true, message: "商品名称不能为空", trigger: "blur" },
          { min: 2, max: 6, message: "长度在 2 到 4 个字符", trigger: "blur" }
        ],
        spPrice: [
          { required: true, message: "价格不能为空", trigger: "blur" },
          { min: 2, max: 6, message: "长度在 2 到 4 个字符", trigger: "blur" }
        ],
        spImage:[
          { required: true, message: "请上传图片", trigger: "blur" },
        ],
     
      },
      // activeName: "first",
      // 对话框添加或删除标记
      addFlag: true,
      // 存储修改行的下标
      updateIndex: "",
      kinds: [],
      kind: {
        zlId: "",
        zlName: ""
      },
      //表格显示数据，与后端进行绑定
      products: [],
      product: {
        unit:'',
        spId: "",
        zlId: "",
        zlName: "",
        spName: "",
        suId: "",
        suName: "",
        spPrice: "",
        spImage: "",
        spProduceDate: "",
        pageNum: "",
        pageSize: ""
      },
      formLabelWidth: "120px",
      supplies: [],
      // 显示添加对话框
      dialogVisible: false,
      // 添加员工绑定属性
      supply: {
        suId: "",
        suName: "",
        suContactor: "",
        suPhone: ""
      },
      // 搜索员工绑定属性
      productDTO: {
        suId: "",
        suName: "",
        spPrice: "",
        spProduceDate: "",
        zlId: "",
        zlName: "",
        spImage: "",
        unit:"",
      },
      // 多选框选中的数据，删除时传输后台这个
      multipleSelection: [],
      // 临时变量，记录多选框删除数据
      selectedRows: [],
      // 选中当前行高亮
      currentRow: null,
      pageNum: 1,
      pageSize: 6,
      pageBean: {}
    };
  },
  mounted() {
    this.search();
    this.searchAllKinds();
    this.searchAllSupplies();
  },
  methods: {
   
    exportExcel() {
      /**
       * 下载文件的功能不能使用axios发请求，因为axios的响应会被封装到res中，在前端没办法处理。
       * 只能使用原生的浏览器直发请求
       */
      console.log(this.product.pageNum);
      let url = "http://localhost:8080/product/export";
      url += "?pageNum=" + this.pageNum;
      url += "&pageSize=" + this.pageSize;
      if (this.product.spId) {
        url += "&spId=" + this.product.spId;
      }

      if (this.product.spName) {
        url += "&spName=" + this.product.spName;
      }
      if (this.product.zlName) {
        url += "&zlName=" + this.product.zlName;
      }
      if (this.product.suName) {
        url += "&suName=" + this.product.suName;
      }
      if (this.product.spPrice) {
        url += "&spPrice=" + this.product.spPrice;
      }
      if (this.product.unit) {
        url += "&unit=" + this.product.unit;
      }
      if (this.product.spProduceDate) {
        url += "&spProduceDate=" + this.product.spProduceDate;
      }

      window.location.href = url;
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.search();
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.search();
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      // if (!isLt2M) {
      //     this.$message.error('上传头像图片大小不能超过 20MB!');
      // }
      return isJPG;
    },
    handleAvatarSuccess(res, file) {
      console.log(res);
      this.product.spImage = res.data;
      console.log(this.product.spImage);
    },
    // 添加人员
    addOrUpdate(product) {
      if (this.addFlag == true) {
        console.log(this.product);

        this.$refs[product].validate((valid)=>{
        if(valid){
          this.$axios.post("http://localhost:8080/product/add", this.product)
        .then((r)=>{
          if(r.data.code===200){
             this.$message.success("添加成功");
              this.search();
               this.dialogVisible = false;
               this.product = {};
          }
          else if(r.data.code===100){
            this.$messag.errore("验证失败"+JSON.stringify(r.data.data))

          }else if(r.data.code===500){
            this.$message.error("注册失败"+JSON.stringify(r.data.data))
          }
         });
        }
      })  
      } else {
        // 修改
        console.log(this.product);
        // 修改
        this.$axios
          .post("http://localhost:8080/product/edit", this.product)
          .then(r => {
            if (r.data.code == 200) {
              this.$message.success("修改成功");
              this.search();
            } else {
              this.$message.error("修改失败");
              this.search();
            }
            this.dialogVisible = false;
            this.addFlag;
            this.product = {};
          });
      }
    },
    // 取消添加人员
    cancle() {
      this.dialogVisible = false;
      // 这样清空真的很快，不用傻乎乎每个属性都赋空
      this.product = {};
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
            .post("http://localhost:8080/product/deleteBySpId/" + row.spId)
            .then(r => {
              if (r.data.code === 200) {
                this.$message.success("删除成功");
                this.search();
              } else {
                this.$message.error("删除失败");
              }
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
      this.addFlag = false;
      this.product = { ...row };
      this.updateIndex = index;
      this.dialogVisible = true;
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
    search() {
      console.log(this.productDTO);
      this.$axios
        .post(
          "http://localhost:8080/product/getAll/" +
            this.pageNum +
            "/" +
            this.pageSize,
          this.productDTO
        )
        .then(r => {
          if (r.data.code === 200) {
            this.pageBean = r.data.data;
            console.log(this.pageBean);
          }
        });
    },
    // 删除所有人员
    removeAll() {
      if (this.multipleSelection.length == 0) {
        this.$message.error("请至少选中一条数据");
        return;
      }
      this.$confirm("此操作将批量删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          let spIds = [];
          for (let m of this.multipleSelection) {
            spIds.push(m.spId);
          }
          this.$axios
            .post("http://localhost:8080/product/removeAll", {
              spIds
            })
            .then(r => {
              if (r.data.code === 200) {
                this.$message.success("删除成功");
                this.search();
              } else {
                this.$message.error("删除失败");
              }
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    // 搜索种类
    searchAllKinds() {
      this.$axios.get("http://localhost:8080/kind/getAllKinds").then(r => {
        if (r.data.code === 200) {
          this.kinds = r.data.data;
        }
      });
    },
    //搜索供货商
    searchAllSupplies() {
      this.$axios.get("http://localhost:8080/supply/getAllSupplies").then(r => {
        if (r.data.code === 200) {
          this.supplies = r.data.data;
        }
      });
    },
    // 搜索重置
    reset() {
      this.productDTO = {};
      this.$message({
        type: "success",
        message: "重置成功"
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
    }
  }
};
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}

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