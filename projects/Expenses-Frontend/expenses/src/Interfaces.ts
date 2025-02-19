export interface AuthContextType {
    login: (username: string, password: string) => Promise<boolean>;
    logout: () => void;
    isAuthenticated: boolean;
    username: string | null;
    token: string | null;
    userId: string | null;
    role: string | null;
  }

export interface UserProfileInterface{
  activatedProfile: boolean;
  address: string| null;
  email: string | null;
  firstName: string | null;
  lastName: string | null;
  phoneNumber: string | null;
  // profilePic: byte[];
  username: string | null;
}

export interface CategoryInterface{
  categoryId:number;
  name:string;
}

export interface ExpenseInterface{
  name:string;
  price:number;
  description:string;
  date:Date;

}
export interface ExpenseCompleteInterface{
  expenseId:number;
  name:string;
  price:number;
  description:string;
  date:Date;
  category:CategoryInterface

}

export interface ExpenseLink{
  add:boolean;
  expenseId:number
}