# Gunakan image Node.js versi LTS sebagai base image
FROM node:14

# Set working directory di dalam container
WORKDIR /usr/src/app

# Salin package.json dan package-lock.json untuk menginstall dependensi
COPY package*.json ./

# Install dependensi
RUN npm install

# Salin seluruh kode aplikasi ke dalam container
COPY . .

# Expose port yang digunakan oleh aplikasi
EXPOSE 3000

# Secret Key JWT
ENV JWT_SECRET='sewain2024'

# Perintah untuk menjalankan aplikasi ketika container dijalankan
CMD ["npm", "start"]
