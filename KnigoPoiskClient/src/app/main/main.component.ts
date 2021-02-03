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
    this.bookcontroller.getAll().subscribe((rec: any) => {
      this.allBooks = rec;
    });
  }

  deleteBook(id: number) {
    this.bookcontroller.deleteById(id).subscribe((rec: any) => {
      this.bookcontroller.getAll().subscribe((record: any) => {
        this.allBooks = record;
      })
    });
  }


}
