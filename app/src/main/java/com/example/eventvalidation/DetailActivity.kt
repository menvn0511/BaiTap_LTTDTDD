package com.example.eventvalidation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eventvalidation.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val student = intent.getSerializableExtra(EXTRA_STUDENT) as? Student

        if (student == null) {
            Toast.makeText(
                this,
                "Chưa có thông tin đăng ký",
                Toast.LENGTH_SHORT
            ).show()

            binding.tvFullName.text = "Chưa có dữ liệu"
            binding.tvEmail.text = "—"
            binding.tvPhone.text = "—"
            binding.tvStudentId.text = "—"
            binding.tvClassName.text = "—"
        } else {
            binding.tvFullName.text = student.fullName
            binding.tvEmail.text = student.email
            binding.tvPhone.text = student.phone
            binding.tvStudentId.text = student.studentId
            binding.tvClassName.text = student.className
        }

        binding.btnBackRegister.setOnClickListener {
            finish()
        }

        binding.btnGoRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_STUDENT = "extra_student"
    }
}