// user.model.ts

export interface UserResponse {
  users: User[];
  statusCode: number;
}


export interface User {
  id: number;
  nev: string;   // Mapped from 'nev'
  email: string;
  avatar?: string;
  jelszo?: string;
  admin: string;
  createdAt?: Date;
}

