import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Participant: Channel class
class Channel {
    private final String name;
    private final int frequency;
    private final String countryOrigin;

    public Channel(String name, int frequency, String countryOrigin) {
        this.name = name;
        this.frequency = frequency;
        this.countryOrigin = countryOrigin;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    @Override
    public String toString() {
        return name + "\t" + countryOrigin + "\t" + frequency;
    }
}

// Participant: Aggregate
class ChannelAggregate {
    private final List<Channel> channels;

    public ChannelAggregate() {
        channels = new ArrayList<>();
    }

    public void add(Channel channel) {
        channels.add(channel);
    }

    public Iterator<Channel> createIterator() {
        return new ChannelIterator();
    }

    public Iterator<Channel> createTurkiyeIterator() {
        return new TurkiyeIterator();
    }

    public Iterator<Channel> createFrequencyIterator(int userNumber) {
        return new FrequencyIterator(userNumber);
    }

    private class ChannelIterator implements Iterator<Channel> {
        private int position = 0;

        @Override
        public boolean hasNext() {
            return position < channels.size();
        }

        @Override
        public Channel next() {
            if (hasNext()) {
                return channels.get(position++);
            }
            return null;
        }
    }

    private class TurkiyeIterator implements Iterator<Channel> {
        private int position = 0;

        @Override
        public boolean hasNext() {
            while (position < channels.size()) {
                if (channels.get(position).getCountryOrigin().equals("Türkiye")) {
                    return true;
                }
                position++;
            }
            return false;
        }

        @Override
        public Channel next() {
            if (hasNext()) {
                return channels.get(position++);
            }
            return null;
        }
    }

    private class FrequencyIterator implements Iterator<Channel> {
        private int position = 0;
        private final int userNumber;

        public FrequencyIterator(int userNumber) {
            this.userNumber = userNumber;
        }

        @Override
        public boolean hasNext() {
            while (position < channels.size()) {
                if (channels.get(position).getFrequency() <= userNumber) {
                    return true;
                }
                position++;
            }
            return false;
        }

        @Override
        public Channel next() {
            if (hasNext()) {
                return channels.get(position++);
            }
            return null;
        }
    }
}

// Main class
public class IteratorPattern {
    public static void main(String[] args) {
        ChannelAggregate aggregate = new ChannelAggregate();
        aggregate.add(new Channel("Das Erste", 10, "Germany"));
        aggregate.add(new Channel("CCTV-1", 657, "China"));
        aggregate.add(new Channel("NOW", 555, "Türkiye"));
        aggregate.add(new Channel("Show Tv", 0, "Türkiye"));
        aggregate.add(new Channel("TVNZ-1", 999, "New Zealand"));
        aggregate.add(new Channel("CNC World", 789, "China"));
        aggregate.add(new Channel("TRT-1", 676, "Türkiye"));
        aggregate.add(new Channel("ZDF", 155, "Germany"));
        aggregate.add(new Channel("Mehwar TV", 56, "Egypt"));

        // Iterating through all channels
        System.out.println("All Channels:");
        Iterator<Channel> iterator = aggregate.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Iterating through Türkiye channels
        System.out.println("\nChannels in Türkiye:");
        Iterator<Channel> turkiyeIterator = aggregate.createTurkiyeIterator();
        while (turkiyeIterator.hasNext()) {
            System.out.println(turkiyeIterator.next());
        }

        // Iterating through channels with frequency less than or equal to 10
        int userNumber = 800; // For demonstration, you can change this value as needed
        System.out.println("\nChannels with frequency less than or equal to " + userNumber + ":");
        Iterator<Channel> frequencyIterator = aggregate.createFrequencyIterator(userNumber);
        while (frequencyIterator.hasNext()) {
            System.out.println(frequencyIterator.next());
        }
    }
}