const PROXY_CONFIG = [
  {
    context: [
      "/api/*",
      "/secure/api/*"
    ],
    target: "http://localhost:8080",
    secure: false
  }
]

module.exports = PROXY_CONFIG;
