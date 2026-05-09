import React from 'react';

const metrics = [
  { label: 'Active users', value: '12.4k' },
  { label: 'Open jobs', value: '842' },
  { label: 'Pending verifications', value: '37' },
  { label: 'Open disputes', value: '11' },
];

const queues = [
  { title: 'Worker verification', count: 18, priority: 'High' },
  { title: 'Employer verification', count: 19, priority: 'High' },
  { title: 'Reported jobs', count: 7, priority: 'Medium' },
  { title: 'Payment disputes', count: 4, priority: 'Medium' },
];

export default function AdminDashboard() {
  return (
    <div className="py-8">
      <div className="mb-8">
        <p className="text-sm font-semibold uppercase tracking-wide text-blue-700">Admin operations</p>
        <h1 className="text-2xl font-bold text-gray-900">Platform Control Center</h1>
      </div>

      <div className="mb-8 grid grid-cols-1 gap-5 md:grid-cols-4">
        {metrics.map((metric) => (
          <div key={metric.label} className="rounded-lg border border-gray-100 bg-white p-5 shadow-sm">
            <p className="text-sm font-medium text-gray-500">{metric.label}</p>
            <p className="mt-2 text-3xl font-bold text-gray-950">{metric.value}</p>
          </div>
        ))}
      </div>

      <div className="grid grid-cols-1 gap-6 lg:grid-cols-[1.2fr_0.8fr]">
        <section className="rounded-lg border border-gray-100 bg-white shadow-sm">
          <div className="border-b border-gray-100 px-6 py-4">
            <h2 className="text-lg font-semibold text-gray-900">Moderation Queue</h2>
          </div>
          <div className="divide-y divide-gray-100">
            {queues.map((queue) => (
              <div key={queue.title} className="flex items-center justify-between px-6 py-4">
                <div>
                  <p className="font-medium text-gray-900">{queue.title}</p>
                  <p className="text-sm text-gray-500">{queue.count} items waiting</p>
                </div>
                <span className="rounded-full bg-blue-50 px-3 py-1 text-sm font-medium text-blue-700">
                  {queue.priority}
                </span>
              </div>
            ))}
          </div>
        </section>

        <section className="rounded-lg border border-gray-100 bg-white p-6 shadow-sm">
          <h2 className="text-lg font-semibold text-gray-900">Today</h2>
          <div className="mt-5 space-y-4">
            <div>
              <p className="text-sm text-gray-500">Jobs completed</p>
              <p className="text-2xl font-bold text-gray-950">126</p>
            </div>
            <div>
              <p className="text-sm text-gray-500">Completion rate</p>
              <p className="text-2xl font-bold text-green-700">92%</p>
            </div>
            <div>
              <p className="text-sm text-gray-500">Top city</p>
              <p className="text-2xl font-bold text-gray-950">Bengaluru</p>
            </div>
          </div>
        </section>
      </div>
    </div>
  );
}
