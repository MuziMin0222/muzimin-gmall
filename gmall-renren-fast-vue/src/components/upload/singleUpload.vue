<template> 
  <div>
    <el-upload
      action='http://192.168.56.11:9000'
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
import {policy} from './policy'
import {getUUID} from '@/utils'

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
      }]
    },
    showFileList: {
      get: function () {
        return this.value !== null && this.value !== '' && this.value !== undefined;
      },
      set: function (newValue) {
      }
    }
  },
  data() {
    return {
      dataObj: {
        policy: ''
      },
      dialogVisible: false
    };
  },
  methods: {
    emitInput(val) {
      this.$emit('input', val)
    },
    handleRemove(file, fileList) {
      this.emitInput('');
    },
    handlePreview(file) {
      this.dialogVisible = true;
    },
    beforeUpload(file) {
      let _self = this;
      return new Promise((resolve, reject) => {
        policy(file).then(response => {
          console.log(response.data)
          console.log(`这是file：${file}`)
          _self.dataObj.policy = response.data;
          resolve(true)
        }).catch(err => {
          reject(new Error(err))
        })
      })
    },
    handleUploadSuccess(res, file) {
      console.log('上传成功...')
      this.showFileList = true;
      this.fileList.pop();
      this.fileList.push({
        name: file.name,
        url: this.dataObj.policy
      });
      this.emitInput(this.fileList[0].url);
    }
  }
}
</script>
<style>

</style>


