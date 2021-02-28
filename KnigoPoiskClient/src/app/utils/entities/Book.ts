export class Book {
  constructor(
    public id: number,
    public title: string,
    public authors: Array<Author>,
    public year: number,
    public isbn: string,
    public languages: Array<Lang>,
    public genres: Array<Genre>,
    public publisher: Publisher,
    public description: string,
    public images: Array<string>
  ) {
  }
}

export class Author {
  constructor(
    public id: number,
    public name: string,
  ) {
  }
}

export class Genre {
  constructor(
    public id: number,
    public name: string,
    public create: string,
    public update: string
  ) {
  }
}

export class Lang {
  constructor(
    public id: number,
    public name: string,
  ) {
  }
}

export class Publisher {
  constructor(
    public id: number,
    public name: string,
    public description: string,
 ) {
  }
}
