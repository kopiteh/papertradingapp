export interface Asset{
  asset_id: string
  price_usd: number
}

export interface MyPortfolio{
  username: string
  asset_id: string
  price_usd: number
  quantity: number
  value: number
}


export interface Leaderboard{
  username : string
  total_value : number
}

export interface User{
  username: string
  password: string
}

export interface newUser extends User{
  email: string
}
export interface JwtToken{
  subject: string
  token: string
}

export interface Order {
  asset_id: string
  price_usd: number
  quantity: number
}


