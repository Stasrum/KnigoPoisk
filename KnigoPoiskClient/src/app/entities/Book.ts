class DefaultBook {
  constructor(
    public created: string,
    public updated: string
  ) {
  }
}

export class Book{
  constructor(
  public id: number,
  public title: string,
  public author: Author,
  public year: number,
  public isbn: string,
  public lang: Lang,
  public genre: Genre,
  public publisher: Publisher,
  public description: string,
  public created: string,
  public updated: string){}
}

export class Author {
  constructor(
    public id: number,
    public name: string,
    public created: string,
    public updated: string
  ) {}
}

export class Genre {
  constructor(
    public id: number,
    public name: string,
    public created: string,
    public updated: string
  ) {}
}

export class Lang {
  constructor(
    public id: number,
    public name: string,
    public created: string,
    public updated: string
  ) {}
}

export class Publisher {
  constructor(
    public id: number,
    public name: string,
    public description: string,
    public created: string,
    public updated: string
  ) {}
}
