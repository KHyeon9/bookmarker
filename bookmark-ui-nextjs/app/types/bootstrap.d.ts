// CSS import 타입 선언
declare module '*.css' {
  const content: Record<string, string>;
  export default content;
}

// Bootstrap JS import 타입 선언
declare module 'bootstrap/dist/js/bootstrap.min.js' {
  const bootstrap: any;
  export default bootstrap;
}
// import 오류 - 타입이 정의되어 있지 않기 때문에 any타입으로 설정