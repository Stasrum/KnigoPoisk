import { Component, OnInit } from '@angular/core';
import {Book} from '../entities/Book';
import {BookController} from '../controllers/BookController';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  public allBooks: Array<Book>;

  constructor(private bookcontroller: BookController) {
  }
  ngOnInit(): void {
    this.bookcontroller.getAllBooks().subscribe((rec: any) => {
      this.allBooks = rec;
      console.log(this.allBooks);
    });
  }
}
