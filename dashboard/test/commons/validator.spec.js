import { regexNumberAndEnglishAndHyphen } from '../../commons/validator.js';
import { expect } from 'chai';

describe('validation test', () => {
  it('success on english', () => {
    const actual = regexNumberAndEnglishAndHyphen('foo');

    expect(actual).to.true;
  });

  it('success on dash', () => {
    const actual = regexNumberAndEnglishAndHyphen('-');

    expect(actual).to.true;
  });

  it('success on number', () => {
    const actual = regexNumberAndEnglishAndHyphen('123');

    expect(actual).to.true;
  });
});

describe('composite word validation test', () => {
  it('success on english and dash', () => {
    const actual = regexNumberAndEnglishAndHyphen('foo');

    expect(actual).to.true;
  });

  it('success on dash', () => {
    const actual = regexNumberAndEnglishAndHyphen('foo-');

    expect(actual).to.true;
  });

  it('success on english and dash and number', () => {
    const actual = regexNumberAndEnglishAndHyphen('foo-123');

    expect(actual).to.true;
  });
});

describe('failure', () => {
  it('fail on korean', () => {
    const actual = regexNumberAndEnglishAndHyphen('안녕');

    expect(actual).to.false;
  });

  it('fail on special symbol', () => {
    const actual = regexNumberAndEnglishAndHyphen('@!#$%^&*_-');

    expect(actual).to.false;
  });

  it('fail on space', () => {
    const actual = regexNumberAndEnglishAndHyphen(' ');

    expect(actual).to.false;
  });
});
