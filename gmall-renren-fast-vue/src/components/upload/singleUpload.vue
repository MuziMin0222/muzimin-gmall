<template>
  <div>
    <el-upload
      action=''
      :data='dataObj'
      list-type='picture'
      :multiple='false' :show-file-list='showFileList'
      :file-list='fileList'
      :before-upload='beforeUpload'
      :on-remove='handleRemove'
      :on-success='handleUploadSuccess'
      :on-preview='handlePreview'>
      <el-button size='small' type='primary'>点击上传</el-button>
      <div slot='tip' class='el-upload__tip'>只能上传jpg/png文件，且不超过10MB</div>
    </el-upload>
    <el-dialog :visible.sync='dialogVisible'>
      <img width='100%' :src='fileList[0].url' alt=''>
    </el-dialog>
  </div>
</template>

<script>
import {policy} from './policy';

export default {
  name: 'singleUpload',
  props: {
    value: String
  },
  computed: {
    imageUrl() {
      return this.value;
    },
    imageName() {
      if (this.value != null && this.value !== '') {
        return this.value.substr(this.value.lastIndexOf('/') + 1);
      } else {
        return null;
      }
    },
    fileList() {
      return [{
        name: this.imageName,
        url: this.imageUrl
      }];
    },
    showFileList: {
      get() {
        return this.value !== null && this.value !== '' && this.value !== undefined;
      },
      set() {
      }
    }
  },
  data() {
    return {
      dataObj: {
        policy: '',
        objectName: '',
        minioPath: ''
      },
      dialogVisible: false
    };
  },
  methods: {
    emitInput(val) {
      console.log('start emitInput')
      this.$emit('input', val);
    },
    handleRemove(file, fileList) {
      console.log('start handleRemove')
      this.emitInput('');
    },
    handlePreview(file) {
      console.log('start handlePreview')
      this.dialogVisible = true;
    },
    beforeUpload(file) {
      let _self = this;
      return new Promise((resolve, reject) => {
        policy(file).then(response => {
          console.log('response：', response);
          _self.dataObj.policy = response.data.host;
          _self.dataObj.name = response.data.name;
          _self.dataObj.minioPath = response.data.url;

          let newFileName = this.dataObj.name;
          let imageType = 'image/' + newFileName.substring(newFileName.lastIndexOf('.') + 1);
          console.log('imageType:', imageType)
          let newFile = new File([file], newFileName, {type: imageType});
          this.$axios.request({
            url: this.dataObj.policy,
            method: 'put',
            data: newFile
          }).then(res => {
            console.log('PUT 请求成功...', res);
            this.showFileList = true;
            this.fileList.pop();
            this.fileList.push({
              name: file.name,
              url: this.dataObj.minioPath
            });
            console.log('filelist', this.fileList)
            this.emitInput(this.fileList[0].url);
          })

          resolve(true);
        }).catch(err => {
          reject(new Error(err));
        });
      });
    },
    handleUploadSuccess(response, file, fileList) {

    }
  }
};
</script>
<style>

</style>
