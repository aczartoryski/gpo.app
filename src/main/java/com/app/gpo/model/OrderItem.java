/*
 * The MIT License
 *
 * Copyright 2017 Artur Czartoryski <artur at czartoryski.wroclaw.pl>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.app.gpo.model;

/**
 *
 * @author Artur Czartoryski <artur at czartoryski.wroclaw.pl>
 */
public class OrderItem {
    CREATE TABLE `gpodb`.`orderitem` (
  `orderItemID` INT NOT NULL AUTO_INCREMENT,
  `orderItemName` VARCHAR(255) NOT NULL,
  `orderItemDueDate` DATE NOT NULL,
  `orderStatusID` INT NOT NULL,
  `orderStatusDate` DATE NULL,
  PRIMARY KEY (`orderItemID`),
  INDEX `fk_orderStatus_idx` (`orderStatusID` ASC),
  CONSTRAINT `fk_orderStatus`
    FOREIGN KEY (`orderStatusID`)
    REFERENCES `gpodb`.`orderstatus` (`orderStatusID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
}
