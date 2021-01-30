import {Component, OnInit} from '@angular/core';
import {Author, Book, Genre, Lang, Publisher} from '../entities/book';
import {Authorcontroller} from '../controllers/authorcontroller';
import {Langcontroller} from '../controllers/langcontroller';
import {Genrecontroller} from '../controllers/genrecontroller';
import {Publishercontroller} from '../controllers/publishercontroller';
import {Bookcontroller} from '../controllers/bookcontroller';

@Component({
  selector: 'app-add-new-book',
  templateUrl: './add-new-book.component.html',
  styleUrls: ['./add-new-book.component.css']
})
export class AddNewBookComponent implements OnInit {
  public newBook = new Book(null, '', null, null, null, null, null, null, '');
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

  constructor(private authorcontroller: Authorcontroller,
              private langcontroller: Langcontroller,
              private genrecontroller: Genrecontroller,
              public publishercontroller: Publishercontroller,
              public bookcontroller: Bookcontroller) {
  }

  ngOnInit(): void {
    this.authorcontroller.getAllAuthor().subscribe((rec: any) => this.authors = rec);
    this.langcontroller.getAllLang().subscribe((rec: any) => this.languages = rec);
    this.genrecontroller.getAllGenre().subscribe((rec: any) => this.genres = rec);
    this.publishercontroller.getAllPublisher().subscribe((rec: any) => this.publishers = rec);
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
          if (rec == author){
          this.authorcontroller.getAllAuthor().subscribe((res: any) => this.authors = res);
          this.visible = false;
          } else {
            this.error = rec;
            console.log(rec);
          }
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
