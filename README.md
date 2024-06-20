## APi User

# POST URL Login
Deskripsi: Endpoint ini digunakan untuk autentikasi pengguna (login) ke sistem Sewain.
Metode: POST
URL: https://sewain-api-user-5b25hvndba-et.a.run.app/auth/login
Body Request: Biasanya berisi data login seperti username/email dan password.
Response: Mengembalikan token atau sesi autentikasi untuk digunakan dalam permintaan API berikutnya.

# POST URL Signup
Deskripsi: Endpoint untuk mendaftar pengguna baru ke dalam sistem Sewain.
Metode: POST
URL: https://sewain-api-user-5b25hvndba-et.a.run.app/auth/signup
Body Request: Berisi informasi yang diperlukan untuk membuat akun baru, seperti nama pengguna, alamat email, dan kata sandi.
Response: Biasanya mengembalikan status keberhasilan pendaftaran atau pesan kesalahan jika terjadi.

# POST URL Logout
Deskripsi: Endpoint untuk logout pengguna dari sistem Sewain.
Metode: POST
URL: https://sewain-api-user-5b25hvndba-et.a.run.app/auth/logout
Body Request: Terkadang tidak memerlukan data tambahan karena identitas pengguna telah dikenali dari token autentikasi yang dihapus.
Response: Biasanya mengembalikan status keberhasilan logout atau pesan kesalahan jika terjadi.
