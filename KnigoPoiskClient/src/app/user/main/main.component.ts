import {Component, OnInit} from '@angular/core';
import {BookController} from '../../utils/controllers/BookController';
import {Book} from '../../utils/entities/Book';
import {path} from '../../utils/entities/Constant';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  public allBooks: Array<Book>;
  public pageadle: Array<any> = new Array(2);
  public size = 3;
  public number = 1;
  public activeNumber: Array<string> = new Array<string>();
  public activeSize: Array<string> = new Array<string>();
  public author = '';
  public genre = '';
  public title = '';
  public visible = false;
  public id: number;
  public path = path;

  constructor(private bookcontroller: BookController) {
  }

  ngOnInit(): void {
    this.bookcontroller.getAll(1, this.size).subscribe((rec: any) => {
      this.allBooks = rec.content;
      this.pageadle[0] = rec.pageable.pageNumber;
      this.pageadle[1] = rec.pageable.totalPages;
      this.pageadle[2] = rec.pageable.pageSize;
      this.pageadle[3] = new Array(rec.totalPages);
      this.activeNumber[1] = 'select';
      this.activeSize[3] = 'select'
      console.log(this.pageadle);
    });
  }

  pageable(number: number) {
    this.number = number;
    this.activeNumber = new Array<string>();
    this.activeNumber[number] = "select"
    this.bookcontroller.getAll(number, this.size, this.title, this.author, this.genre).subscribe((rec: any) => {
      this.allBooks = rec.content;
      this.pageadle[0] = rec.pageable.pageNumber;
      this.pageadle[1] = rec.totalPages;
      this.pageadle[2] = rec.pageable.pageSize;
      this.pageadle[3] = new Array(rec.totalPages);
      console.log(this.pageadle);
    });
  }

  pageadleSize(size: number) {
    this.size = size / 10;
    this.activeSize = new Array<string>();
    this.activeSize[size / 10] = 'select';
    this.activeNumber = new Array<string>();
    this.activeNumber[1] = "select"
    this.bookcontroller.getAll(1, size / 10, this.title, this.author, this.genre).subscribe((rec: any) => {
      this.allBooks = rec.content;
      this.pageadle[0] = rec.pageable.pageNumber;
      this.pageadle[1] = rec.totalPages;
      this.pageadle[2] = rec.pageable.pageSize;
      this.pageadle[3] = new Array(rec.totalPages);
      console.log(this.pageadle);
    });
  }

  findBook() {
    this.bookcontroller.getAll(1, this.size, this.title, this.author, this.genre).subscribe((rec: any) => {
      this.allBooks = rec.content;
      this.pageadle[0] = rec.pageable.pageNumber;
      this.pageadle[1] = rec.totalPages;
      this.pageadle[2] = rec.pageable.pageSize;
      this.pageadle[3] = new Array(rec.totalPages);
      console.log(this.pageadle);
    });
  }
}
