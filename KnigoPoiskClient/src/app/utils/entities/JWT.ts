export class Jwt {
  constructor(
    public sub: string,
    public roles: Array<string>,
    public exp: string,
    public iat: string
  ) {}
}
