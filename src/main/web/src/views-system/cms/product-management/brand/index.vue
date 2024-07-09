<template>
  <div class="app-container">
    <div style="float: right;">
      <el-form @submit.native.prevent>
        <el-form-item>
          <el-input
            v-model="listQuery.filter"
            placeholder="Tìm kiếm"
            @keyup.enter.native="getList"
          />
        </el-form-item>
      </el-form>
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      :default-sort="sortDefault"
      @sort-change="sortChange"
    >
      <el-table-column label="STT" align="center" width="100">
        <template slot-scope="scope">
          <span>#{{ scope.$index + 1 }}</span>
        </template>
      </el-table-column>

      <el-table-column label="Tên" prop="name" sortable="custom" align="center" />

      <el-table-column label="Mô tả" prop="description" sortable="custom" align="center" />

      <el-table-column label="Trạng thái" prop="description" sortable="custom" align="center" width="150">
        <template slot-scope="{row}">
          <span v-if="row.status === 'ACTIVE'">
            <el-tag type="success" effect="dark">Hoạt động</el-tag>
          </span>
          <span v-if="row.status === 'IN_ACTIVE'">
            <el-tag type="danger" effect="dark" />
          </span>
        </template>
      </el-table-column>

      <el-table-column label="" align="center" width="150">
        <template slot-scope="{row}">
          <el-button type="primary" size="mini" @click="openModalEdit(row.id)">
            Chỉnh sửa
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.currentPage" :limit.sync="listQuery.perPage" @pagination="getList" />

    <el-dialog :title="brand.id ? 'Chỉnh sửa' : 'Thêm mới'" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="brand" label-position="left">
        <el-form-item label="Tên" prop="name">
          <el-input v-model="brand.name" />
        </el-form-item>

        <el-form-item label="Mô tả" prop="description">
          <Tinymce ref="editor" v-model="brand.description" :height="400" style="margin-top: 35px;" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeModal()">
          Hủy
        </el-button>
        <el-button type="primary" @click="edit()">
          {{ brand.id ? 'Chỉnh sửa' : 'Thêm mới' }}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { brandGetAllPage, brandGetById, brandCreateOrUpdate } from '@/api/brand'
import Pagination from '@/components/Pagination'
import Tinymce from '@/components/Tinymce'

export default {
  name: 'Category',
  components: { Pagination, Tinymce },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        currentPage: 1,
        perPage: 10,
        filter: '',
        sortBy: '',
        sortDesc: ''
      },
      sortDefault: {
        prop: '',
        order: ''
      },
      dialogFormVisible: false,
      brand: {
        id: null,
        name: null,
        description: null,
        createdAt: null,
        status: null
      }
    }
  },
  watch: {
  },
  created() {
    this.getList()
  },
  mounted() {
  },
  destroyed() {
  },
  methods: {
    getList() {
      this.listLoading = true
      brandGetAllPage(this.listQuery).then(res => {
        this.list = res.items
        this.total = res.totalRows

        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    sortChange(sortChange) {
      this.listQuery.sortBy = sortChange.prop
      this.listQuery.sortDesc = sortChange.order === 'descending'
      this.getList()
    },
    openModalEdit(id) {
      brandGetById(id).then(res => {
        this.dialogFormVisible = true
        this.brand = res.data
      })
    },
    closeModal() {
      this.dialogFormVisible = false
      this.brand.id = null
      this.brand.name = null
      this.brand.description = null
      this.brand.createdAt = null
      this.brand.status = null
    },
    edit() {
      brandCreateOrUpdate(this.brand).then(res => {
        this.dialogFormVisible = false
        this.getList()
      })
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
