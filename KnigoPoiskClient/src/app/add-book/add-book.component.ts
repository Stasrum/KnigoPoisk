import {Component, OnInit} from '@angular/core';
import {Author, Book, Genre, Lang, Publisher} from '../entities/Book';
import {AuthorController} from '../controllers/AuthorController';
import {LangController} from '../controllers/LangController';
import {GenreController} from '../controllers/GenreController';
import {PublisherController} from '../controllers/PublisherController';
import {BookController} from '../controllers/BookController';
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-add-new-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {
  public newBook:Book;
  public authors: Array<Author>;
  public languages: Array<Lang>;
  public genres: Array<Genre>;
  public publishers: Array<Publisher>;
  public visible = false;
  public addNew: string;
  public newObject: string;
  public newName: string;
  public newText: string;
  public error = {
    defaultMessage: undefined
  };
  private subscription: Subscription;
  //public editBook: Book;

  constructor(private authorcontroller: AuthorController,
              private langcontroller: LangController,
              private genrecontroller: GenreController,
              public publishercontroller: PublisherController,
              private bookcontroller: BookController,
              private activateRoute: ActivatedRoute) {
  this.subscription = activateRoute.params.subscribe((rec:any) => this.newBook = rec);

  }

  ngOnInit(): void {
    this.authorcontroller.getAllAuthor().subscribe((rec: any) => this.authors = rec);
    this.langcontroller.getAllLang().subscribe((rec: any) => this.languages = rec);
    this.genrecontroller.getAllGenre().subscribe((rec: any) => this.genres = rec);
    this.publishercontroller.getAllPublisher().subscribe((rec: any) => this.publishers = rec);
    console.log(this.newBook);
    if(!this.newBook){
      this.newBook = new Book(null, '', null, null, null, null, null, null, '');
    }
  }



  addNewBook() {
    console.log(this.newBook);
    this.bookcontroller.createBook(this.newBook).subscribe(error => console.log(error));
  }

  addNewObject() {
    switch (this.addNew) {
      case 'жанр': {
        const genre = new Genre(null, this.newName);
        console.log(genre);
        this.genrecontroller.createGenre(genre).subscribe(rec => {
          this.genrecontroller.getAllGenre().subscribe((res: any) => this.genres = res);
        });
        break;
      }
      case 'автора': {
        const author = new Author(null, this.newName);
        console.log(author);
        this.authorcontroller.createAuthor(author).subscribe((rec: any) => {
          this.authorcontroller.getAllAuthor().subscribe((res: any) => this.authors = res);
        });
        break;
      }
      case 'язык': {
        const lang = new Lang(null, this.newName);
        console.log(lang);
        this.langcontroller.createLang(lang).subscribe(rec => {
          this.langcontroller.getAllLang().subscribe((res: any) => this.languages = res);
        });
        break;
      }
      case 'издателя': {
        const publisher = new Publisher(null, this.newName, this.newText);
        console.log(publisher);
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
}
