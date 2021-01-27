import { Component, OnInit } from '@angular/core';
import {addBook, Book} from '../entities/book';

@Component({
  selector: 'app-add-new-book',
  templateUrl: './add-new-book.component.html',
  styleUrls: ['./add-new-book.component.css']
})
export class AddNewBookComponent implements OnInit {
  public newBook = new addBook('', null, null, '', null, null, null, '');
  constructor() { }

  ngOnInit(): void {
  }

  addNewBook() {
    console.log(this.newBook);
  }
}
