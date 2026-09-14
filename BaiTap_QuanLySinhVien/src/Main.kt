data class Student(
    val studentID: String,
    val fullName: String,
    val age: Int,
    val major: String,
    val gpa: Double
)
fun hienThiSinhVien(student: Student) {
    println("Student ID: ${student.studentID}")
    println("Full Name: ${student.fullName}")
    println("Age: ${student.age}")
    println("Major: ${student.major}")
    println("GPA: ${student.gpa}")
    println("--------------------------------------------")
}
// 1. Thêm sinh viên
fun themSinhVien(students: MutableList<Student>) {
    print("Nhap Student ID: ")
    val studentID = readLine()!!
    // Kiểm tra ID đã tồn tại chưa
    for (student in students) {
        if (student.studentID == studentID) {
            println("Student ID da ton tai")
            return
        }
    }
    print("Nhap Full Name: ")
    val fullName = readLine()!!
    print("Nhap Age: ")
    val age = readLine()!!.toInt()
    print("Nhap Major: ")
    val major = readLine()!!
    print("Nhap GPA: ")
    val gpa = readLine()!!.toDouble()
    val student = Student(
        studentID,
        fullName,
        age,
        major,
        gpa
    )
    students.add(student)
    println("Them sinh vien thanh cong")
}
// 2. Hiển thị tất cả sinh viên
fun hienThiTatCaSinhVien(students: MutableList<Student>) {
    if (students.isEmpty()) {
        println("Danh sach sinh vien dang trong")
        return
    }
    println("============ DANH SACH SINH VIEN ============")
    for (student in students) {
        hienThiSinhVien(student)
    }
}
// 3. Tìm kiếm sinh viên
fun timKiemSinhVien(students: MutableList<Student>) {
    var choice: Int
    do {
        println()
        println("============== SEARCH STUDENT ==============")
        println("1. Tim sinh vien theo nganh")
        println("2. Tim sinh vien theo mot phan ten")
        println("3. Tin sinh vien co GPA tu 7.0 den 8.5")
        println("0. Quay lai")
        println("============================================")
        print("Choose: ")
        choice = readLine()!!.toInt()
        when (choice) {
            // Yêu cầu 7
            1 -> {
                print("Nhap nganh can tim: ")
                val major = readLine()!!
                var found = false
                for (student in students) {
                    if (student.major.equals(major, ignoreCase = true)) {
                        hienThiSinhVien(student)
                        found = true
                    }
                }
                if (!found) {
                    println("Khong tim thay sinh vien thuoc nganh nay")
                }
            }
            // Yêu cầu 8
            2 -> {
                print("nhap mot phan ten can tim: ")
                val name = readLine()!!
                var found = false
                for (student in students) {
                    if (student.fullName.contains(name, ignoreCase = true)) {
                        hienThiSinhVien(student)
                        found = true
                    }
                }
                if (!found) {
                    println("khong tim thay sinh vien")
                }
            }
            // Yêu cầu 6
            3 -> {
                var found = false
                println("Sinh vien co GPA tu 7.0 den 8.5:")
                for (student in students) {
                    if (student.gpa >= 7.0 && student.gpa <= 8.5) {
                        hienThiSinhVien(student)
                        found = true
                    }
                }
                if (!found) {
                    println("khong co sinh vien nao phu hop")
                }
            }
            0 -> {
                println("Quay lai menu chinh")
            }
            else -> {
                println("Lua chon khong hop le")
            }
        }
    } while (choice != 0)
}
// 4. Tính GPA trung bình
fun tinhGpaTrungBinh(students: MutableList<Student>) {
    if (students.isEmpty()) {
        println("Danh sach sinh vien dang trong")
        return
    }
    // Tính GPA trung bình của tất cả sinh viên
    var totalGpa = 0.0
    for (student in students) {
        totalGpa = totalGpa + student.gpa
    }
    val averageGpa = totalGpa / students.size
    println("GPA trung binh cua tat ca sinh vien: %.2f".format(averageGpa))
    // Yêu cầu 3
    print("nhap nganh can tinh GPA trung binh: ")
    val major = readLine()!!
    var majorTotalGpa = 0.0
    var majorCount = 0

    for (student in students) {
        if (student.major.equals(major, ignoreCase = true)) {
            majorTotalGpa = majorTotalGpa + student.gpa
            majorCount++
        }
    }
    if (majorCount == 0) {
        println("Khong co sinh vien thuoc nganh $major.")
    } else {
        val majorAverageGpa = majorTotalGpa / majorCount
        println(
            "GPA trung binh nganh $major: %.2f".format(majorAverageGpa)
        )
    }
}
// 5. Tìm sinh viên có GPA cao nhất
fun timSinhVienCaoNhat(students: MutableList<Student>) {
    if (students.isEmpty()) {
        println("Danh sach sinh vien dang trong")
        return
    }
    var choice: Int
    do {
        println()
        println("========== FIND STUDENT ===========")
        println("1. Tim sinh vien co GPA cao nhat")
        println("2. Tim Sinh vien lon tuoi nhat")
        println("3. Hien thi top 3 sinh vien co GPA cao nhat")
        println("0. Quay lai")
        println("===================================")
        print("Choose: ")
        choice = readLine()!!.toInt()
        when (choice) {
            // Yêu cầu 4
            1 -> {
                var highestGpa = students[0].gpa
                for (student in students) {
                    if (student.gpa > highestGpa) {
                        highestGpa = student.gpa
                    }
                }
                println("Sinh vien co GPA cao nhat:")
                for (student in students) {
                    if (student.gpa == highestGpa) {
                        hienThiSinhVien(student)
                    }
                }
            }
            // Yêu cầu 5
            2 -> {
                var oldestAge = students[0].age
                for (student in students) {
                    if (student.age > oldestAge) {
                        oldestAge = student.age
                    }
                }
                println("Sinh vien lon tuoi nhat:")
                for (student in students) {
                    if (student.age == oldestAge) {
                        hienThiSinhVien(student)
                    }
                }
            }
            // Yêu cầu 10
            3 -> {
                val sortedStudents = students.toMutableList()
                sortedStudents.sortByDescending { it.gpa }
                println("========== TOP 3 SINH VIEN ==========")
                var count = 0
                for (student in sortedStudents) {
                    if (count < 3) {
                        hienThiSinhVien(student)
                        count++
                    }
                }
            }
            0 -> {
                println("Quay lai menu chinh.")
            }
            else -> {
                println("Lua chon khong hop le")
            }
        }
    } while (choice != 0)
}
// 6. Xóa sinh viên
fun xoaSinhVien(students: MutableList<Student>) {
    print("Nhap sinh vien can xoa: ")
    val studentID = readLine()!!
    var found = false
    for (student in students) {
        if (student.studentID == studentID) {
            students.remove(student)
            found = true
            println("Xoa sinh vien thanh cong")
            break
        }
    }
    if (!found) {
        println("Khong tin thay sinh vien co Student ID nay")
    }
}
// Các yêu cầu về thống kê và sắp xếp
fun thongKeVaSapXep(students: MutableList<Student>) {
    var choice: Int
    do {
        println()
        println("========== THONG KE VA SAP XEP ==========")
        println("1. Dem sinh vien co GPA >= 8.0")
        println("2. Dem sinh vien co GPA < 5.0")
        println("3. Sap xep sinh vien theo GPA giam dan")
        println("4. Sap xep sinh vien theo tuoi")
        println("5. Sap xep sinh vien theo ten")
        println("0. Quay lai")
        println("=========================================")
        print("Choose: ")
        choice = readLine()!!.toInt()
        when (choice) {
            // Yêu cầu 1
            1 -> {
                var count = 0
                for (student in students) {
                    if (student.gpa >= 8.0) {
                        count++
                    }
                }
                println("So sinh vien co GPA >= 8.0: $count")
            }
            // Yêu cầu 2
            2 -> {
                var count = 0
                for (student in students) {
                    if (student.gpa < 5.0) {
                        count++
                    }
                }
                println("So sinh vien co GPA < 5.0: $count")
            }
            // Yêu cầu 9
            3 -> {
                val sortedStudents = students.toMutableList()

                sortedStudents.sortByDescending { it.gpa }

                println("Danh sach sinh vien theo GPA giam dan:")

                for (student in sortedStudents) {
                    hienThiSinhVien(student)
                }
            }
            // Yêu cầu 11
            4 -> {
                val sortedStudents = students.toMutableList()
                sortedStudents.sortBy { it.age }
                println("Danh sach sinh vien theo tuoi tang dan:")
                for (student in sortedStudents) {
                    hienThiSinhVien(student)
                }
            }
            // Yêu cầu 12
            5 -> {
                val sortedStudents = students.toMutableList()
                sortedStudents.sortBy { it.fullName }
                println("Danh sach sinh vien theo ten:")
                for (student in sortedStudents) {
                    hienThiSinhVien(student)
                }
            }
            0 -> {
                println("Quay lai menu chinh.")
            }
            else -> {
                println("lua chon khong hop le")
            }
        }
    } while (choice != 0)
}
fun main() {
    // 5 sinh viên mẫu riêng theo yêu cầu đề bài
    val students = mutableListOf(
        Student(
            "SV001",
            "Nguyen Thi Tra My",
            20,
            "Cong nghe thong tin",
            8.5
        ),
        Student(
            "SV002",
            "Bui le Huyen My",
            21,
            "Ke toan",
            7.2
        ),
        Student(
            "SV003",
            "Dang Thi Anh Tho",
            22,
            "Cong nghe thong tin",
            9.1
        ),
        Student(
            "SV004",
            "Pham Thi Thao Nguyen",
            20,
            "Marketing",
            4.5
        ),
        Student(
            "SV005",
            "Phan Khanh Nhu",
            23,
            "Cong nghe thong tin",
            6.8
        )
    )
    var choice: Int
    do {
        println()
        println("============ STUDENT MANAGEMENT ============")
        println("1. Add student")
        println("2. Display all students")
        println("3. Search student")
        println("4. Calculate average GPA")
        println("5. Find student with highest GPA")
        println("6. Remove student")
        println("0. Exit")
        println("============================================")
        print("Choose: ")
        choice = readLine()!!.toInt()
        when (choice) {
            1 -> {
                themSinhVien(students)
            }
            2 -> {
                hienThiTatCaSinhVien(students)
                println()
                println("Su dung cac chuc nang sap xep")
                thongKeVaSapXep(students)
            }
            3 -> {
                timKiemSinhVien(students)
            }
            4 -> {
                tinhGpaTrungBinh(students)
            }
            5 -> {
                timSinhVienCaoNhat(students)
            }
            6 -> {
                xoaSinhVien(students)
            }
            0 -> {
                println("Thoat chuong trinh")
            }
            else -> {
                println("Lua chon khong hop le")
            }
        }
    } while (choice != 0)
}