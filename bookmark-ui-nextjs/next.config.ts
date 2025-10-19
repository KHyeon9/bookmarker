import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  // 개발 모드 검수를 도와줌(개발 환경에서만 작동)
  reactStrictMode: true,
  async redirects() {
    return [
      // '/' 주소를 '/bookmarks'로 리다이렉트함
      {
        source: '/',
        destination: '/bookmarks',
        permanent: true, // 영구적으로 작동
      }
    ]
  }
};

export default nextConfig;
