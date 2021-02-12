export class LoginUser {
  constructor(
    public username: string,
    public password: string
  ) {}
}

export class UserDto{
  constructor(
    public userName: string,
    public firstName: string,
    public lastName: string,
    public password: string,
    public matchingPassword: string,
    public email: string,
    public birthDay: string
  ) {
  }
}
