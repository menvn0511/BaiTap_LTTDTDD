
package com.ute.btlayout

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtHoTen = findViewById<EditText>(R.id.edtHoTen)
        val edtMSSV = findViewById<EditText>(R.id.edtMSSV)
        val edtLop = findViewById<EditText>(R.id.edtLop)
        val rgGioiTinh = findViewById<RadioGroup>(R.id.rgGioiTinh)
        val swThongBao = findViewById<SwitchCompat>(R.id.swThongBao)
        val btnLuu = findViewById<Button>(R.id.btnLuu)
        val tvKetQua = findViewById<TextView>(R.id.tvKetQua)

        btnLuu.setOnClickListener {
            val hoTen = edtHoTen.text.toString().trim()
            val mssv = edtMSSV.text.toString().trim()
            val lop = edtLop.text.toString().trim()

            if (hoTen.isEmpty() || mssv.isEmpty() || lop.isEmpty()) {
                Toast.makeText(
                    this,
                    "Vui lòng nhập đầy đủ thông tin",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val gioiTinh = when (rgGioiTinh.checkedRadioButtonId) {
                R.id.rbNam -> "Nam"
                R.id.rbNu -> "Nữ"
                else -> "Chưa chọn"
            }

            val thongBao = if (swThongBao.isChecked) "Có" else "Không"

            tvKetQua.text = """
                THÔNG TIN ĐÃ LƯU
                Họ tên: $hoTen
                MSSV: $mssv
                Lớp: $lop
                Giới tính: $gioiTinh
                Nhận thông báo: $thongBao
            """.trimIndent()

            Toast.makeText(this, "Lưu thông tin thành công", Toast.LENGTH_SHORT).show()
        }
    }
}
