FROM node:22

# Buat direktori kerja
WORKDIR /usr/src/app

# Salin file package.json dan package-lock.json
COPY package*.json ./

# Instal dependensi
RUN npm install

# Salin semua file proyek
COPY . .

# Ekspose port yang digunakan aplikasi
EXPOSE 8080

# Jalankan perintah untuk memulai server
CMD ["npm", "start"]
