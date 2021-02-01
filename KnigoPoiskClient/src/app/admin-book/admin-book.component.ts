import {Component, OnInit} from '@angular/core';
import {Author, Book, Genre, Lang, Publisher} from '../entities/Book';
import {AuthorController} from '../controllers/AuthorController';
import {LangController} from '../controllers/LangController';
import {GenreController} from '../controllers/GenreController';
import {PublisherController} from '../controllers/PublisherController';
import {BookController} from '../controllers/BookController';
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-add-new-book',
  templateUrl: './admin-book.component.html',
  styleUrls: ['./admin-book.component.css']
})
export class AdminBookComponent implements OnInit {
  public newBook = new Book(null, '', null, null, null, null, null, null, '', '', '');
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
  public edit: Array<number> = [0, 0, 0, 0];


  constructor(
    private router: Router,
    private authorcontroller: AuthorController,
    private langcontroller: LangController,
    private genrecontroller: GenreController,
    public publishercontroller: PublisherController,
    private bookcontroller: BookController,
    private activateRoute: ActivatedRoute) {
    this.subscription = activateRoute.params.subscribe(rec=> this.id = rec.id);
  }

  ngOnInit(): void {
    if(this.id) {
      this.bookcontroller.findById(this.id).subscribe((rec: any) => {
        this.newBook = rec;
        this.edit[0] = this.newBook.genre.id;
        this.edit[1] = this.newBook.author.id;
        this.edit[2] = this.newBook.lang.id;
        this.edit[3] = this.newBook.publisher.id;
      });
    }
    this.authorcontroller.getAllAuthor().subscribe((rec: any) => this.authors = rec);
    this.langcontroller.getAllLang().subscribe((rec: any) => this.languages = rec);
    this.genrecontroller.getAllGenre().subscribe((rec: any) => this.genres = rec);
    this.publishercontroller.getAllPublisher().subscribe((rec: any) => this.publishers = rec);
  }

  addNewBook() {
    this.newBook.genre = this.genres.filter(rec => rec.id == this.edit[0])[0];
    this.newBook.author = this.authors.filter(rec => rec.id == this.edit[1])[0];
    this.newBook.lang = this.languages.filter(rec => rec.id == this.edit[2])[0];
    this.newBook.publisher = this.publishers.filter(rec => rec.id == this.edit[3])[0];
    this.bookcontroller.create(this.newBook).subscribe(error => {
      console.log(error);
      this.router.navigateByUrl('');
    });
  }

  addNewObject() {
    switch (this.addNew) {
      case 'жанр': {
        const genre = new Genre(null, this.newName, '', '');
        this.genrecontroller.createGenre(genre).subscribe(rec => {
          this.genrecontroller.getAllGenre().subscribe((res: any) => this.genres = res);
        });
        break;
      }
      case 'автора': {
        const author = new Author(null, this.newName, '', '');
        this.authorcontroller.createAuthor(author).subscribe((rec: any) => {
          this.authorcontroller.getAllAuthor().subscribe((res: any) => this.authors = res);
        });
        break;
      }
      case 'язык': {
        const lang = new Lang(null, this.newName, '', '');
        this.langcontroller.createLang(lang).subscribe(rec => {
          this.langcontroller.getAllLang().subscribe((res: any) => this.languages = res);
        });
        break;
      }
      case 'издателя': {
        const publisher = new Publisher(null, this.newName, this.newText, '', '');
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
