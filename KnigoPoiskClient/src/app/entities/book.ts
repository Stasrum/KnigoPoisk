export class Book {
  constructor(
  public id: number,
  public title: string,
  public author: Autor,
  public year: number,
  public isbn: string,
  public lang: Lang,
  public genre: Genre,
  public publisher: Publisher,
  public description: string){}
}

export class addBook {
  constructor(
    public title: string,
    public author: number,
    public year: number,
    public isbn: string,
    public lang: number,
    public genre: number,
    public publisher: number,
    public description: string){}
}

export class Autor {
  constructor(
    public id: number,
    public name: string
  ) {}
}

export class Genre {
  constructor(
    public id: number,
    public name: string
  ) {}
}

export class Lang {
  constructor(
    public id: number,
    public name: string
  ) {}
}

export class Publisher {
  constructor(
    public id: number,
    public name: string,
    public description: string
  ) {}
}
