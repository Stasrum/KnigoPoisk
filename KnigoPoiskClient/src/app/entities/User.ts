export class loginUser {
  constructor(
    public username: string,
    public password: string
  ) {}
}

export class userDto{
  constructor(
    public userName: string,
    public firstName: string,
    public lastName: string,

  ) {
  }
}
