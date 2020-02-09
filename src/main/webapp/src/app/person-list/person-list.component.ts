import {Component, OnDestroy, OnInit} from '@angular/core';
import {Person} from "../model/person";
import {PersonService} from "../service/person-service.service";
import {MessageService} from "../service/message-service.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent implements OnInit, OnDestroy {
  personsArray: Person[];
  subscription: Subscription;

  constructor(private personService: PersonService, private messageService: MessageService) {
    this.subscription = this.messageService.createPerson.subscribe(message => {
      this.requestPersons();
    });
  }

  delete(person:Person){
    this.personService.delete(person).subscribe(reponse=>{
      this.requestPersons();
    })
  }

  edit(person:Person){
    this.messageService.editPerson.emit(Object.assign({}, person)); // create copy of object to prevent sync change in table
  }

  ngOnInit() {
    this.requestPersons();
  }

  requestPersons() {
    this.personService.findAll().subscribe(data => {
      this.personsArray = data;
    });
  }

  ngOnDestroy() {
    // unsubscribe to ensure no memory leaks
    this.subscription.unsubscribe();
  }

}
