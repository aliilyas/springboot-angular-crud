import {EventEmitter, Injectable, Output} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {Person} from "../model/person";

@Injectable({providedIn: 'root'})
export class MessageService {
  @Output() createPerson: EventEmitter<Person> = new EventEmitter();
  @Output() editPerson: EventEmitter<Person> = new EventEmitter();
}
