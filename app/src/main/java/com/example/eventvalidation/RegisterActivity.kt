package com.example.eventvalidation

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eventvalidation.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private var registeredStudent: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            registerStudent()
        }

        binding.btnGoDetail.setOnClickListener {
            val student = registeredStudent

            if (student == null) {
                Toast.makeText(
                    this,
                    "Vui lòng đăng ký hợp lệ trước!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_STUDENT, student)
                startActivity(intent)
            }
        }
    }

    private fun registerStudent() {
        val fullName = binding.edtFullName.text.toString().trim()
        val email = binding.edtEmail.text.toString().trim()
        val phone = binding.edtPhone.text.toString().trim()
        val studentId = binding.edtStudentId.text.toString().trim()
        val className = binding.edtClassName.text.toString().trim()

        binding.tilFullName.error = null
        binding.tilEmail.error = null
        binding.tilPhone.error = null
        binding.tilStudentId.error = null
        binding.tilClassName.error = null

        var isValid = true

        if (fullName.isEmpty()) {
            binding.tilFullName.error = "Vui lòng nhập họ tên"
            isValid = false
        } else if (
            fullName.length < 2 ||
            !fullName.matches(Regex("^[\\p{L}][\\p{L}\\s'-]*$"))
        ) {
            binding.tilFullName.error = "Họ tên không hợp lệ"
            isValid = false
        }

        if (email.isEmpty()) {
            binding.tilEmail.error = "Vui lòng nhập email"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Email không đúng định dạng"
            isValid = false
        }

        if (phone.isEmpty()) {
            binding.tilPhone.error = "Vui lòng nhập số điện thoại"
            isValid = false
        } else if (!phone.matches(Regex("^0\\d{9}$"))) {
            binding.tilPhone.error =
                "Số điện thoại phải có 10 chữ số và bắt đầu bằng 0"
            isValid = false
        }

        if (studentId.isEmpty()) {
            binding.tilStudentId.error = "Vui lòng nhập mã sinh viên"
            isValid = false
        } else if (!studentId.matches(Regex("^[A-Za-z0-9]{5,15}$"))) {
            binding.tilStudentId.error = "MSV gồm 5-15 ký tự chữ hoặc số"
            isValid = false
        }

        if (className.isEmpty()) {
            binding.tilClassName.error = "Vui lòng nhập lớp"
            isValid = false
        } else if (!className.matches(Regex("^[\\p{L}\\p{N}]+$"))) {
            binding.tilClassName.error =
                "Tên lớp không được chứa khoảng trắng hoặc ký tự đặc biệt"
            isValid = false
        }

        if (!isValid) {
            registeredStudent = null

            Toast.makeText(
                this,
                "Đăng ký thất bại! Vui lòng kiểm tra thông tin.",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        registeredStudent = Student(
            fullName = fullName,
            email = email,
            phone = phone,
            studentId = studentId,
            className = className
        )

        Toast.makeText(
            this,
            "Đăng ký thành công!",
            Toast.LENGTH_SHORT
        ).show()
    }
}