import {Component, OnInit} from '@angular/core';
import {Author, Book, Genre, Lang, Publisher} from '../../utils/entities/Book';
import {AuthorController} from '../../utils/controllers/AuthorController';
import {LangController} from '../../utils/controllers/LangController';
import {GenreController} from '../../utils/controllers/GenreController';
import {PublisherController} from '../../utils/controllers/PublisherController';
import {BookController} from '../../utils/controllers/BookController';
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-add-new-book',
  templateUrl: './admin-book.component.html',
  styleUrls: ['./admin-book.component.css']
})
export class AdminBookComponent implements OnInit {
  public newBook = new Book(null, '', null, null, null, [], [], null, null, null);
  public authors: Array<Author>;
  public languages: Array<Lang>;
  public genres: Array<Genre>;
  public publishers: Array<Publisher>;
  public visible = false;
  public addNew: string;
  public newObject: string;
  public newName: string;
  public newText: string;
  private subscription: Subscription;
  public id: number;
  addAuthors: Array<number> = [-1, 0];
  addLanguages: Array<number> = [-1, 0];
  addGenres: Array<number> = [-1, 0];
  addPublisher = 0;

  constructor(
    private router: Router,
    private authorcontroller: AuthorController,
    private langcontroller: LangController,
    private genrecontroller: GenreController,
    public publishercontroller: PublisherController,
    private bookcontroller: BookController,
    private activateRoute: ActivatedRoute) {
    this.subscription = activateRoute.params.subscribe(rec => this.id = rec.id);
  }

  ngOnInit(): void {
    if (this.id) {
      this.bookcontroller.findById(this.id).subscribe((rec: any) => {
        this.newBook = rec;
        for (let i = 0; i < this.newBook.genres.length; i++) {
          this.addGenres[i + 1] = this.newBook.genres[i].id;
        }
        this.addGenres.push(0);
        for (let i = 0; i < this.newBook.authors.length; i++) {
          this.addAuthors[i + 1] = this.newBook.authors[i].id;
        }
        this.addAuthors.push(0);
        for (let i = 0; i < this.newBook.languages.length; i++) {
          this.addLanguages[i + 1] = this.newBook.languages[i].id;
        }
        this.addLanguages.push(0);
        this.addPublisher = this.newBook.publisher.id;
      });
    }
    this.authorcontroller.getAllAuthor().subscribe((rec: any) => {
      this.authors = rec;
    });
    this.langcontroller.getAllLang().subscribe((rec: any) => this.languages = rec);
    this.genrecontroller.getAllGenre().subscribe((rec: any) => this.genres = rec);
    this.publishercontroller.getAllPublisher().subscribe((rec: any) => this.publishers = rec);
  }

  addNewBook() {
    this.newBook.genres = this.genres.filter(rec => this.addGenres.includes(rec.id));
    this.newBook.authors = this.authors.filter(rec => this.addAuthors.includes(rec.id));
    this.newBook.languages = this.languages.filter(rec => this.addLanguages.includes(rec.id));
    this.newBook.publisher = this.publishers.find(rec => rec.id == this.addPublisher);
    if (this.id) {
      this.bookcontroller.edit(this.newBook).subscribe(error => {
        console.log(this.newBook);
        this.router.navigateByUrl('/editbook');
      });
    } else {
      this.bookcontroller.add(this.newBook).subscribe(error => {
        console.log(this.newBook);
        this.router.navigateByUrl('/editbook');
      });
    }
  }

  addNewObject() {
    switch (this.addNew) {
      case 'жанр': {
        const genre = new Genre(null, this.newName, null, null);
        this.genrecontroller.createGenre(genre).subscribe(rec => {
          this.genrecontroller.getAllGenre().subscribe((res: any) => this.genres = res);
        });
        break;
      }
      case 'автора': {
        const author = new Author(null, this.newName);
        this.authorcontroller.createAuthor(author).subscribe((rec: any) => {
          this.authorcontroller.getAllAuthor().subscribe((res: any) => this.authors = res);
        });
        break;
      }
      case 'язык': {
        const lang = new Lang(null, this.newName);
        this.langcontroller.createLang(lang).subscribe(rec => {
          this.langcontroller.getAllLang().subscribe((res: any) => this.languages = res);
        });
        break;
      }
      case 'издателя': {
        const publisher = new Publisher(null, this.newName, this.newText);
        this.publishercontroller.createPublisher(publisher).subscribe(rec => {
          this.publishercontroller.getAllPublisher().subscribe((res: any) => this.publishers = res);
        });
        break;
      }
    }
  }

  visibleAdd(addNew: string) {
    this.visible = true;
    this.addNew = addNew;
  }

  addSelect(array: Array<any>) {
    array = array.filter((rec:any) => rec != 0);
    array.push(0);
    return array;
  }
}
