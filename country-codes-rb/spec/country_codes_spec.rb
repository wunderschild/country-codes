# frozen_string_literal: true

RSpec.describe CountryCodes do
  it 'has a version number' do
    expect(CountryCodes::VERSION).not_to be nil
  end

  it 'allows all correct search fields' do
    expect do
      described_class.find('foo', search_fields: CountryCodes::SEARCHABLE_FIELDS)
    end.not_to raise_error
  end

  it 'declines invalid search fields' do
    expect do
      described_class.find('foo', search_fields: %i[some random words])
    end.to raise_error(ArgumentError)
  end

  it 'creates LookupTable instance correctly' do
    expect(described_class.instance).to be_a(CountryCodes::LookupTable)
  end

  context 'with locale: en' do
    it 'finds France by name' do
      result = described_class.find('france', locale: 'en')

      expect(result).to be_a(CountryCodes::Country)
    end

    it 'finds France by nationality' do
      result = described_class.find('french', locale: 'en')

      expect(result).to be_a(CountryCodes::Country)
    end

    it 'finds France with weird case style' do
      result = described_class.find('fRAncE', locale: 'en')

      expect(result).to be_a(CountryCodes::Country)
    end

    it 'does not find a nonexistent country' do
      result = described_class.find('Xen', locale: 'en')

      expect(result).to be_nil
    end
  end

  context 'with locale: ru' do
    it 'finds Russia by simple name' do
      result = described_class.find('россия', locale: 'ru')

      expect(result).to be_a(CountryCodes::Country)
    end

    it 'finds Russia by official name' do
      result = described_class.find('российская федерация', locale: 'ru')

      expect(result).to be_a(CountryCodes::Country)
    end

    it 'does not find nonexistent country' do
      result = described_class.find('сити-17', locale: 'ru')

      expect(result).to be_nil
    end
  end

  context "with 'alpha2' search field" do
    it 'finds France by alpha2' do
      result = described_class.find('fr', search_fields: %i[alpha2])

      expect(result).to be_a(CountryCodes::Country)
    end

    it 'finds France by alpha2 in weird case style' do
      result = described_class.find('fR', search_fields: %i[alpha2])

      expect(result).to be_a(CountryCodes::Country)
    end

    it 'does not find nonexistent alpha2 code' do
      result = described_class.find('xn', search_fields: %i[alpha2])

      expect(result).to be_nil
    end
  end
end
