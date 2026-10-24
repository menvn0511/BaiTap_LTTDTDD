package com.ute.studentprofile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ute.studentprofile.databinding.ActivityMainBinding
import com.ute.studentprofile.model.Student
import com.ute.studentprofile.utils.toAcademicRanking
import com.ute.studentprofile.utils.toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentStudent = Student(
        id = "2415053122223",
        name = "Pham Thi Men",
        className = "126LTTD03",
        email = "2415053122223v@ute.udn.vn",
        gpa = 3.2
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Gán dữ liệu ban đầu lên các Views
        bindStudentData(currentStudent)
        binding.btnUpdateGpa.setOnClickListener {

            val inputStr = binding.edtNewGpa.text.toString().trim()
            val newGpa = inputStr.toDoubleOrNull()

            if (newGpa == null || newGpa !in 0.0..4.0) {

                binding.edtNewGpa.error = "Vui lòng nhập GPA hợp lệ (0.0 - 4.0)"
                toast("Điểm GPA không hợp lệ!")

                return@setOnClickListener
            }

            currentStudent = currentStudent.copy(gpa = newGpa)

            bindStudentData(currentStudent)

            toast("Cập nhật điểm thành công!")
        }
    }

    private fun bindStudentData(student: Student) {

        with(binding) {

            tvName.text = student.name

            tvStudentId.text =
                "MSSV: ${student.id} • Lớp: ${student.className}"

            tvGpaBadge.text =
                "${student.gpa} GPA (${student.gpa.toAcademicRanking()})"

            edtNewGpa.setText(student.gpa.toString())
        }
    }
}