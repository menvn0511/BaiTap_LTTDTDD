fun main() {
    val name = "Phạm Thị Mến"
    val studentID = "2415053122223"
    val math: Float = 8.5f
    val programming: Float = 8.7f
    val database: Float = 9.1f
	val total: Float = math + programming + database
	val gpa = total / 3f
	val highest = maxOf(math, programming, database)
    println("Họ và tên: $name")
    println("Mã sinh viên: $studentID")
    println("Tổng điểm: %.2f".format(total))
	println("Điểm trung bình: %.2f".format(gpa))
	println("Điểm cao nhất: %.2f".format(highest))
    if (gpa >= 5.0f) {
    	println("Sinh viên có đạt không?: Đạt")
	} else {
    	println("Sinh viên có đạt không?: Không đạt")
	}
}